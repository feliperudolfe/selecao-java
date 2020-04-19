package com.indracompany.selecaojava.persistencia.modelo.dto;

import com.indracompany.comuns.modelo.dto.DTO;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   19 de abr de 2020
 */
public class MunicipioDTO extends DTO {

	/**
	 *
	 */
	private static final long serialVersionUID = 2529106369125444512L;

	private Long codigo;
	private String nome;
	private EstadoDTO uf;

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

	public EstadoDTO getUf() {
		return uf;
	}

	public void setUf(EstadoDTO uf) {
		this.uf = uf;
	}

}