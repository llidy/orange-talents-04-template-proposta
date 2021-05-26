package br.com.zupacademy.lidiane.proposta.sistemasexternos.cartao;

public class CriaCartaoRequest {
	
	private String documento;
	private String nome;
	private Long idProposta;
	
	@Deprecated
	public CriaCartaoRequest () {};
	
	public CriaCartaoRequest(String documento, 
							 String nome, 
							 Long idProposta) {
		super();
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
