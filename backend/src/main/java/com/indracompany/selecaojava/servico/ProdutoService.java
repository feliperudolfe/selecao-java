package com.indracompany.selecaojava.servico;

import javax.transaction.Transactional;

import com.indracompany.selecaojava.persistencia.modelo.entidade.Produto;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   18 de abr de 2020
 */
public interface ProdutoService {

	/**
	 * Serviço responsável pela persistência das informações relacionadas a produto
	 *
	 * @author 	Felipe Rudolfe Carvalho Pinheiro
	 * @since   18 de abr de 2020
	 * @param 	produto
	 */
	@Transactional
	Produto salvar(Produto produto);
}