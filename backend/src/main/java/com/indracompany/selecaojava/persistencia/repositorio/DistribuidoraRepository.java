package com.indracompany.selecaojava.persistencia.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.indracompany.selecaojava.persistencia.modelo.entidade.Distribuidora;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   18 de abr de 2020
 */
@Repository
public interface DistribuidoraRepository extends CrudRepository<Distribuidora, Long> {

	Distribuidora findByCnpj(String cnpj);

}