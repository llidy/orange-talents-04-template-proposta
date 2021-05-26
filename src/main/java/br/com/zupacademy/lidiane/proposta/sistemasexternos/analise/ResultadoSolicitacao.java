package br.com.zupacademy.lidiane.proposta.sistemasexternos.analise;

import br.com.zupacademy.lidiane.proposta.novaProposta.Status;

public enum ResultadoSolicitacao {
	
	COM_RESTRICAO(Status.NAO_ELEGIVEL), 
	SEM_RESTRICAO(Status.ELEGIVEL);
	
	private Status status;
	
	ResultadoSolicitacao(Status status) {
		this.status = status;
	}

	public Status getStatus() {
		return status;
	}

	
}
