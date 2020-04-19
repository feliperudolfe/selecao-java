package com.indracompany.selecaojava.persistencia.modelo.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import com.indracompany.comuns.converter.CnpjSerializer;
import com.indracompany.comuns.modelo.dto.DTO;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   19 de abr de 2020
 */
public class DistribuidoraDTO extends DTO {

	/**
	 *
	 */
	private static final long serialVersionUID = -2379327220004482926L;

	private Long codigo;

	@JsonSerialize(using = CnpjSerializer.class)
	@JsonDeserialize(using = StringDeserializer.class)
	private String cnpj;

	private String nome;
	private MunicipioDTO municipio;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public MunicipioDTO getMunicipio() {
		return municipio;
	}

	public void setMunicipio(MunicipioDTO municipio) {
		this.municipio = municipio;
	}

}