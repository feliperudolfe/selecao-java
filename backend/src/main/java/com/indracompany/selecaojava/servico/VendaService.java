package com.indracompany.selecaojava.servico;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.scheduling.annotation.Async;

import com.indracompany.selecaojava.persistencia.modelo.dto.VendaDTO;
import com.indracompany.selecaojava.persistencia.modelo.dto.VendaPaginadorDTO;
import com.indracompany.selecaojava.persistencia.modelo.entidade.Venda;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   18 de abr de 2020
 */
public interface VendaService {

	/**
	 * Serviço responsável pela persistência das informações relacionadas a venda
	 *
	 * @author 	Felipe Rudolfe Carvalho Pinheiro
	 * @since   18 de abr de 2020
	 * @param 	venda
	 */
	@Transactional
	Venda salvar(Venda venda);

	/**
	 * Serviço responsável pela persistência das informações relacionadas a venda
	 *
	 * @author 	Felipe Rudolfe Carvalho Pinheiro
	 * @since   18 de abr de 2020
	 * @param 	vendas
	 */
	@Async
	@Transactional
	void salvar(List<Venda> vendas);

	/**
	 * Listar de forma paginada registros de vendas
	 *
	 * @author 	Felipe Rudolfe Carvalho Pinheiro
	 * @since   19 de abr de 2020
	 * @param 	paginator
	 */
	VendaPaginadorDTO listar(VendaPaginadorDTO paginator);

	/**
	 * Serviço para exclusão de venda
	 *
	 * @author 	Felipe Rudolfe Carvalho Pinheiro
	 * @since   19 de abr de 2020
	 * @param 	codigo
	 */
	void remover(Long codigo);

	/**
	 * Serviço para obter venda por código
	 *
	 * @author 	Felipe Rudolfe Carvalho Pinheiro
	 * @since   19 de abr de 2020
	 * @param 	codigo
	 */
	VendaDTO obterPorCodigo(@Valid @NotNull Long codigo);

	/**
	 * Serviço para atualizar venda
	 *
	 * @author 	Felipe Rudolfe Carvalho Pinheiro
	 * @since   19 de abr de 2020
	 * @param 	codigo
	 * @param 	vendaDTO
	 */
	void atualizar(Long codigo, VendaDTO vendaDTO);

	/**
	 * Obter média de preço por municipio e/ou bandeira
	 *
	 * @author 	Felipe Rudolfe Carvalho Pinheiro
	 * @since   20 de abr de 2020
	 * @param 	municipio
	 * @param 	nomeMunicipio
	 * @param 	bandeira
	 */
	VendaDTO obterMedia(Long municipio, String nomeMunicipio, Long bandeira);

}