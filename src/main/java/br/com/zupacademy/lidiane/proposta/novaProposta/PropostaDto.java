package br.com.zupacademy.lidiane.proposta.novaProposta;

public class PropostaDto {
	private String nome;
	private String documento;
	private Status status;
	
	public PropostaDto(Proposta proposta) {
		this.nome = proposta.getNome();
		this.documento = proposta.getDocumento();
		this.status = proposta.getStatus();
	}
	
	public String getNome() {
		return nome;
	}
	public String getDocumento() {
		return documento;
	}
	public Status getStatus() {
		return status;
	}

}
