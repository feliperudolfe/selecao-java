package com.indracompany.selecaojava.persistencia.dao.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.indracompany.selecaojava.persistencia.modelo.entidade.Bandeira;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   18 de abr de 2020
 */
@Repository
public interface BandeiraRepository extends CrudRepository<Bandeira, Long> {

	Bandeira findByNome(String nome);

}