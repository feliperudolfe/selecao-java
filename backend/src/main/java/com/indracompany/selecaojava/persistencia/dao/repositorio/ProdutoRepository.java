package com.indracompany.selecaojava.persistencia.dao.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.indracompany.selecaojava.persistencia.modelo.entidade.Bandeira;
import com.indracompany.selecaojava.persistencia.modelo.entidade.Produto;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   18 de abr de 2020
 */
@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Long> {

	Produto findByNome(String nome);

	Produto findByNomeAndBandeira(String nome, Bandeira bandeira);

}