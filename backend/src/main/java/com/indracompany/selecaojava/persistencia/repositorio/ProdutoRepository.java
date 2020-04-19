package com.indracompany.selecaojava.persistencia.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.indracompany.selecaojava.persistencia.modelo.dto.OptionDTO;
import com.indracompany.selecaojava.persistencia.modelo.entidade.Bandeira;
import com.indracompany.selecaojava.persistencia.modelo.entidade.Produto;
import com.indracompany.selecaojava.persistencia.query.ProdutoQuery;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   18 de abr de 2020
 */
@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Long> {

	Produto findByNome(String nome);

	Produto findByNomeAndBandeira(String nome, Bandeira bandeira);

	@Query(ProdutoQuery.LIST_OPTIONS)
	List<OptionDTO> listOptions();

	Produto findByCodigo(Long codigo);

}