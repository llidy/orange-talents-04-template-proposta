package br.com.zupacademy.lidiane.proposta.novaProposta;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/propostas")
public class NovaPropostaController {
	
	@Autowired
	private PropostaRepository propostaRepository;
	
	@PostMapping
	public ResponseEntity<?> cadastra(@RequestBody @Valid NovaPropostaRequest request,
									   UriComponentsBuilder uribuilder){
		
		if(propostaRepository.existsByDocumento(request.getDocumento())) {
			return ResponseEntity.unprocessableEntity().build();
		}
		
		Proposta novaProposta = request.paraProposta();
		
		propostaRepository.save(novaProposta);
		
		
		URI location = uribuilder.path("/api/proposta/{id}")
								 .build(novaProposta.getId());
								 
		return ResponseEntity.created(location).build();
	}

}
