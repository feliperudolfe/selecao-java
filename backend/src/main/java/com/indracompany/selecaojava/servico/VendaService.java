package com.indracompany.selecaojava.servico;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.scheduling.annotation.Async;

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

}