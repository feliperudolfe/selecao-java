package com.indracompany.selecaojava.servico;

import java.util.List;

import javax.transaction.Transactional;

import com.indracompany.selecaojava.persistencia.modelo.dto.OptionDTO;
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

	/**
	 * Listar distribuidoras no formato options
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
	Distribuidora consultarPorCodigo(Long codigo);
}