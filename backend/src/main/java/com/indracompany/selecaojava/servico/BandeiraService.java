package com.indracompany.selecaojava.servico;

import javax.transaction.Transactional;

import com.indracompany.selecaojava.persistencia.modelo.entidade.Bandeira;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   18 de abr de 2020
 */
public interface BandeiraService {

	/**
	 * Serviço responsável pela persistência das informações relacionadas a bandeira
	 *
	 * @author 	Felipe Rudolfe Carvalho Pinheiro
	 * @since   18 de abr de 2020
	 * @param 	bandeira
	 */
	@Transactional
	Bandeira salvar(Bandeira bandeira);
}