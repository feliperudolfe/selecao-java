package com.indracompany.selecaojava.persistencia.modelo.dto;

import com.indracompany.comuns.modelo.dto.DTO;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   19 de abr de 2020
 */
public class OptionDTO extends DTO {

	/**
	 *
	 */
	private static final long serialVersionUID = -5365485624934744596L;

	private Long codigo;
	private String valor;

	public OptionDTO() {
		super();
	}

	public OptionDTO(Long codigo, String valor) {//SONAR
		super();
		this.codigo = codigo;
		this.valor = valor;
	}

	public OptionDTO(Long codigo, String valor, String descricao) {//SONAR
		super();
		this.codigo = codigo;
		this.valor = valor + " - " + descricao;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}