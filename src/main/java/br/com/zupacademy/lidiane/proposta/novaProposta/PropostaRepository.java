package br.com.zupacademy.lidiane.proposta.novaProposta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long> {
	
	boolean existsByDocumento(String Documento);

}
