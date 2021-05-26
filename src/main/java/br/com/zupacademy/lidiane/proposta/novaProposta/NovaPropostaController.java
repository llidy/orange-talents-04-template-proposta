package br.com.zupacademy.lidiane.proposta.novaProposta;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.lidiane.proposta.cartao.Cartao;
import br.com.zupacademy.lidiane.proposta.cartao.CartaoRepository;
import br.com.zupacademy.lidiane.proposta.sistemasexternos.analise.AnaliseDePropostaRequest;
import br.com.zupacademy.lidiane.proposta.sistemasexternos.analise.AnaliseDePropostaResponse;
import br.com.zupacademy.lidiane.proposta.sistemasexternos.analise.AnaliseSolicitacaoClient;
import br.com.zupacademy.lidiane.proposta.sistemasexternos.cartao.CriaCartaoClient;
import br.com.zupacademy.lidiane.proposta.sistemasexternos.cartao.CriaCartaoRequest;
import br.com.zupacademy.lidiane.proposta.sistemasexternos.cartao.CriaCartaoResponse;
import feign.FeignException;

@RestController
@RequestMapping("/api/propostas")
public class NovaPropostaController {

	@Autowired
	private PropostaRepository propostaRepository;
	@Autowired
	private CartaoRepository cartaoRepository;
	@Autowired
	private AnaliseSolicitacaoClient analiseClient;
	@Autowired
	private CriaCartaoClient cartaoClient;

	@PostMapping
	public ResponseEntity<?> cadastra(@RequestBody @Valid NovaPropostaRequest request,
			UriComponentsBuilder uribuilder) {

		if (propostaRepository.existsByDocumento(request.getDocumento())) {
			return ResponseEntity.unprocessableEntity().build();
		}

		Proposta novaProposta = request.paraProposta();

		propostaRepository.save(novaProposta);

		try {
			AnaliseDePropostaRequest analiseRequest = new AnaliseDePropostaRequest(novaProposta.getDocumento(),
																				   novaProposta.getNome(), 
																				   novaProposta.getId());

			AnaliseDePropostaResponse resultadoDaConsulta = analiseClient.consulta(analiseRequest);
			Status status = resultadoDaConsulta.status();

			novaProposta.setStatus(status);

			CriaCartaoRequest cartaoRequest = new CriaCartaoRequest(novaProposta.getDocumento(), 
																	novaProposta.getNome(),
																	novaProposta.getId());

			CriaCartaoResponse cartaoResponse = cartaoClient.criaCartao(cartaoRequest);

			Cartao cartao = new Cartao(cartaoResponse.getId());

			cartaoRepository.save(cartao);

			novaProposta.setCartao(cartao);

			propostaRepository.save(novaProposta);
		} catch (FeignException.UnprocessableEntity unprocessableEntity) {
			novaProposta.setStatus(Status.NAO_ELEGIVEL);

		} catch (FeignException.ServiceUnavailable ex) {
			propostaRepository.delete(novaProposta);
		}

		propostaRepository.save(novaProposta);

		URI location = uribuilder.path("/api/propostas/{id}").build(novaProposta.getId());
			return ResponseEntity.created(location).build();
	}

	
}
