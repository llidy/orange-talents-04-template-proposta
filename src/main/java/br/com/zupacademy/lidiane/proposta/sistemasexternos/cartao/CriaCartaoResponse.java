package br.com.zupacademy.lidiane.proposta.sistemasexternos.cartao;


import java.time.LocalDateTime;


public class CriaCartaoResponse {
	
	private String id;
	private LocalDateTime emitidoEm;
	
	
	
	
	public CriaCartaoResponse(String id, LocalDateTime emitidoEm) {
		this.id = id;
		this.emitidoEm = emitidoEm;
	}


	public String getId() {
		return id;
	}


	public LocalDateTime getEmitidoEm() {
		return emitidoEm;
	}
	
}
