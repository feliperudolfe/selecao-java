package com.indracompany.selecaojava.persistencia.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.indracompany.selecaojava.persistencia.modelo.dto.OptionDTO;
import com.indracompany.selecaojava.persistencia.modelo.entidade.Distribuidora;
import com.indracompany.selecaojava.persistencia.query.DistribuidoraQuery;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   18 de abr de 2020
 */
@Repository
public interface DistribuidoraRepository extends CrudRepository<Distribuidora, Long> {

	Distribuidora findByCnpj(String cnpj);

	@Query(DistribuidoraQuery.LIST_OPTIONS)
	List<OptionDTO> listOptions();

	Distribuidora findByCodigo(Long codigo);

}