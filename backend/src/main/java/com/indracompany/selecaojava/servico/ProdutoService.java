package com.indracompany.selecaojava.servico;

import java.util.List;

import javax.transaction.Transactional;

import com.indracompany.selecaojava.persistencia.modelo.dto.OptionDTO;
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

	/**
	 * Listar produtos no formato options
	 *
	 * @author 	Felipe Rudolfe Carvalho Pinheiro
	 * @since   19 de abr de 2020
	 */
	List<OptionDTO> listarOptions();

	/**
	 * Serviço para consulta por códgio
	 *
	 * @author 	Felipe Rudolfe Carvalho Pinheiro
	 * @since   19 de abr de 2020
	 * @param 	codigo
	 */
	Produto consultarPorCodigo(Long codigo);
}