package com.indracompany.selecaojava.persistencia.modelo.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.indracompany.comuns.converter.RegiaoSerializer;
import com.indracompany.comuns.modelo.dto.DTO;
import com.indracompany.selecaojava.persistencia.modelo.tipo.RegiaoEnum;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   19 de abr de 2020
 */
public class EstadoDTO extends DTO {

	/**
	 *
	 */
	private static final long serialVersionUID = 3369550738961152231L;

	private String codigo;

	@JsonSerialize(using = RegiaoSerializer.class)
	private RegiaoEnum regiao;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public RegiaoEnum getRegiao() {
		return regiao;
	}

	public void setRegiao(RegiaoEnum regiao) {
		this.regiao = regiao;
	}

}