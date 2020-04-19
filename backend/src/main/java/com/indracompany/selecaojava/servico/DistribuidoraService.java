package com.indracompany.selecaojava.servico;

import javax.transaction.Transactional;

import com.indracompany.selecaojava.persistencia.modelo.entidade.Distribuidora;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   18 de abr de 2020
 */
public interface DistribuidoraService {

	/**
	 * Serviço responsável pela persistência das informações relacionadas a distribuidora
	 *
	 * @author 	Felipe Rudolfe Carvalho Pinheiro
	 * @since   18 de abr de 2020
	 * @param 	distribuidora
	 */
	@Transactional
	Distribuidora salvar(Distribuidora distribuidora);
}