package br.com.zupacademy.lidiane.proposta.cartao;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Embeddable
public class Cartao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String numeroCartao;
	
	/**
	 * @deprecated apenas para o uso do hibernate
	 */
	@Deprecated
	public Cartao() { } 

	public Cartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public Long getId() {
		return id;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}
	
}
