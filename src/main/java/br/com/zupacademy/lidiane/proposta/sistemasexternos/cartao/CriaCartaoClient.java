package br.com.zupacademy.lidiane.proposta.sistemasexternos.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "cria-cartao", url = "http://localhost:8888/")
public interface CriaCartaoClient {
	
	@PostMapping(value = "/api/cartoes", produces = MediaType.APPLICATION_JSON_VALUE)
	CriaCartaoResponse criaCartao(@RequestBody CriaCartaoRequest request);
	
}
