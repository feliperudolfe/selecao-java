package com.indracompany.selecaojava.servico;

import javax.transaction.Transactional;

import com.indracompany.selecaojava.persistencia.modelo.entidade.Municipio;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   18 de abr de 2020
 */
public interface MunicipioService {

	/**
	 * Serviço responsável pela persistência das informações relacionadas a municipio
	 *
	 * @author 	Felipe Rudolfe Carvalho Pinheiro
	 * @since   18 de abr de 2020
	 * @param 	municipio
	 */
	@Transactional
	Municipio salvar(Municipio municipio);
}