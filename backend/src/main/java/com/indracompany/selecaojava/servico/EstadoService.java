package com.indracompany.selecaojava.servico;

import javax.transaction.Transactional;

import com.indracompany.selecaojava.persistencia.modelo.entidade.Estado;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   18 de abr de 2020
 */
public interface EstadoService {

	/**
	 * Serviço responsável pela persistência das informações relacionadas a estado
	 *
	 * @author 	Felipe Rudolfe Carvalho Pinheiro
	 * @since   18 de abr de 2020
	 * @param 	estado
	 */
	@Transactional
	Estado salvar(Estado estado);
}