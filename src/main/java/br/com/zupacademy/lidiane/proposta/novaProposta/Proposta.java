package br.com.zupacademy.lidiane.proposta.novaProposta;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Proposta {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
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
	
	@NotNull
	private String endereco;

	/*
	 * @deprecated apenas para o uso do hibernate
	 */
	@Deprecated
	Proposta() {}
	

	public Proposta(@Email @NotBlank String email, @NotBlank String nome,
			@NotNull @Positive BigDecimal salario, @NotBlank String documento, @NotNull String endereco) {
		
		
		this.email = email;
		this.nome = nome;
		this.salario = salario;
		this.documento = documento;
		this.endereco = endereco;
	}


	public Long getId() {
		return id;
	}	
	
}
