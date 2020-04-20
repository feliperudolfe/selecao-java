package com.indracompany.selecaojava.persistencia.modelo.dto;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.indracompany.comuns.converter.DateSerializer;
import com.indracompany.comuns.modelo.dto.DTO;
import com.indracompany.selecaojava.persistencia.modelo.entidade.Distribuidora;
import com.indracompany.selecaojava.persistencia.modelo.entidade.Produto;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   19 de abr de 2020
 */
public class VendaDTO extends DTO {

	/**
	 *
	 */
	private static final long serialVersionUID = -7254504626073491435L;

	private Long codigo;

	@JsonSerialize(using = DateSerializer.class)
	private Date dataColeta;

	private Double valorVenda;
	private Double valorCompra;
	private ProdutoDTO produto;
	private DistribuidoraDTO distribuidora;

	public VendaDTO() {//SONAR
		super();
	}

	public VendaDTO(Double valorVenda, Double valorCompra) {//SONAR
		super();
		this.valorVenda = valorVenda;
		this.valorCompra = valorCompra;
	}

	public VendaDTO(
			Long codigo,
			Date dataColeta,
			Double valorVenda,
			Double valorCompra,
			Produto produto,
			Distribuidora distribuidora) {//SONAR

		super();
		this.codigo = codigo;
		this.dataColeta = dataColeta;
		this.valorVenda = valorVenda;
		this.valorCompra = valorCompra;
		this.produto = produto.toDTO(ProdutoDTO.class);
		this.distribuidora = distribuidora.toDTO(DistribuidoraDTO.class);
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Date getDataColeta() {
		return dataColeta;
	}

	public void setDataColeta(Date dataColeta) {
		this.dataColeta = dataColeta;
	}

	public Double getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(Double valorVenda) {
		this.valorVenda = valorVenda;
	}

	public Double getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(Double valorCompra) {
		this.valorCompra = valorCompra;
	}

	public ProdutoDTO getProduto() {
		return produto;
	}

	public void setProduto(ProdutoDTO produto) {
		this.produto = produto;
	}

	public DistribuidoraDTO getDistribuidora() {
		return distribuidora;
	}

	public void setDistribuidora(DistribuidoraDTO distribuidora) {
		this.distribuidora = distribuidora;
	}

}