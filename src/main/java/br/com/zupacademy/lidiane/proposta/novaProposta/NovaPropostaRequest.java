package br.com.zupacademy.lidiane.proposta.novaProposta;


import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


public class NovaPropostaRequest {
	
	@Email
	@NotBlank
	private String email;
	
	@NotBlank
	private String nome;
	
	@NotNull
	@Positive
	private BigDecimal salario;
	
	@NotBlank
	@Documento
	private String documento;
	
	
	private String endereco;


	public NovaPropostaRequest(String email, String nome, BigDecimal salario, String documento,
			String endereco) {
		super();
		
		this.email = email;
		this.nome = nome;
		this.salario = salario;
		this.documento = documento;
		this.endereco = endereco;
		
	}


	@Override
	public String toString() {
		return "NovaPropostaRequest [email=" + email + ", nome=" + nome + ", salario=" + salario
				+ ", documento=" + documento + ", endereco=" + endereco + "]";
	}

    public Proposta paraProposta() {
    	return new Proposta(email, nome, salario, documento, endereco);
    }


	public String getDocumento() {
		return documento;
	}


	public void setDocumento(String documento) {
		this.documento = documento;
	}


	

	
	
	
	
}
