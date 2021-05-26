package br.com.zupacademy.lidiane.proposta.novaProposta;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zupacademy.lidiane.proposta.sistemasexternos.cartao.CriaCartaoResponse;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long> {
	
	boolean existsByDocumento(String Documento);

	List<Proposta> findByStatus(Status status);
}
