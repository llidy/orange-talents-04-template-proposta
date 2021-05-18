package br.com.zupacademy.lidiane.proposta.novaProposta;

public class AnaliseDePropostaRequest {
	
	private String documento;
	private String nome;
	private Long idProposta;
	
	
	/*
	 * apenas para uso do framework
	 */
	@Deprecated
	public AnaliseDePropostaRequest () {}
	
	public AnaliseDePropostaRequest(String documento, 
									String nome, 
									Long idProposta) {
		
		this.documento = documento;
		this.nome = nome;
		this.idProposta = idProposta;
	}

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public Long getIdProposta() {
		return idProposta;
	}
	
	

	
	
	
}
