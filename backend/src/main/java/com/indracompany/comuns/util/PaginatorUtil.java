package com.indracompany.comuns.util;

import com.indracompany.comuns.modelo.dto.DTO;
import com.indracompany.comuns.modelo.dto.PaginadorDTO;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   19 de abr de 2020
 */
public class PaginatorUtil {

	private PaginatorUtil() {}

	public static Integer calcularIndiceInicial(PaginadorDTO<? extends DTO> paginacaoDTO) {
		return paginacaoDTO.getCurrentPage() * paginacaoDTO.getSizePage();
	}

	public static Integer calcularIndiceFinal(PaginadorDTO<? extends DTO> paginacaoDTO) {
		return paginacaoDTO.getSizePage();
	}

}