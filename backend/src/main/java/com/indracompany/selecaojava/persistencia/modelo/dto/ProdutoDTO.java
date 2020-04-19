package com.indracompany.selecaojava.persistencia.modelo.dto;

import com.indracompany.comuns.modelo.dto.DTO;
import com.indracompany.selecaojava.persistencia.modelo.tipo.UnidadeMedidaEnum;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   19 de abr de 2020
 */
public class ProdutoDTO extends DTO {

	/**
	 *
	 */
	private static final long serialVersionUID = -941619256211029276L;

	private Long codigo;
	private String nome;
	private UnidadeMedidaEnum unidadeMedida;
	private BandeiraDTO bandeira;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public UnidadeMedidaEnum getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(UnidadeMedidaEnum unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

	public BandeiraDTO getBandeira() {
		return bandeira;
	}

	public void setBandeira(BandeiraDTO bandeira) {
		this.bandeira = bandeira;
	}

}