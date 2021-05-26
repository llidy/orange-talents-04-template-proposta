package br.com.zupacademy.lidiane.proposta.sistemasexternos.cartao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.zupacademy.lidiane.proposta.cartao.Cartao;
import br.com.zupacademy.lidiane.proposta.cartao.CartaoRepository;
import br.com.zupacademy.lidiane.proposta.novaProposta.Proposta;
import br.com.zupacademy.lidiane.proposta.novaProposta.PropostaRepository;
import br.com.zupacademy.lidiane.proposta.novaProposta.Status;
import br.com.zupacademy.lidiane.proposta.sistemasexternos.analise.AnaliseDePropostaRequest;
import br.com.zupacademy.lidiane.proposta.sistemasexternos.analise.AnaliseDePropostaResponse;
import feign.FeignException;

@Component
public class VerificadorNovaProposta {

	private static final long SEGUNDO = 1000;
	private static final long MINUTO = SEGUNDO * 60;
	private static final long HORA = MINUTO * 60;

	@Autowired
	private PropostaRepository propostaRepository;
	
	@Autowired
	private CriaCartaoClient cartaoClient;
	
	@Autowired
	private CartaoRepository cartaoRepository;

	@Scheduled(fixedDelay = HORA)
	public void consulta() {
		List<Proposta> propostas = propostaRepository.findByStatus(Status.NAO_ELEGIVEL);

		propostas.stream()
			.forEach(proposta -> {
				CriaCartaoRequest cartaoRequest = new CriaCartaoRequest(proposta.getDocumento(), proposta.getNome(), proposta.getId());
				
				CriaCartaoResponse cartaoResponse = cartaoClient.criaCartao(cartaoRequest);
				
				Cartao cartao = new Cartao(cartaoResponse.getId());
				cartaoRepository.save(cartao);
				
				proposta.setCartao(cartao);
				proposta.setStatus(Status.ELEGIVEL);
				propostaRepository.save(proposta);
				
			});
	}

}
