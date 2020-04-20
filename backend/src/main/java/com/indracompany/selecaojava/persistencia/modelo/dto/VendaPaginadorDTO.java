package com.indracompany.selecaojava.persistencia.modelo.dto;

import java.util.Date;
import java.util.List;

import javax.ws.rs.QueryParam;

import com.indracompany.comuns.modelo.dto.PaginadorDTO;
import com.indracompany.selecaojava.persistencia.modelo.tipo.RegiaoEnum;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   19 de abr de 2020
 */
public class VendaPaginadorDTO extends PaginadorDTO<VendaDTO> {

	/**
	 *
	 */
	private static final long serialVersionUID = -7823976973357387428L;

	public VendaPaginadorDTO(Integer currentPage, Integer sizePage, Date dataColeta, Long distribuidora, RegiaoEnum regiao) {
		super((currentPage != null) ? currentPage : 0 , (sizePage != null) ? sizePage : 100);
		this.dataColeta = dataColeta;
		this.distribuidora = distribuidora;
		this.regiao = regiao;
	}

	public VendaPaginadorDTO(Integer currentPage, Integer sizePage, Long count, List<VendaDTO> list) {
		super((currentPage != null) ? currentPage : 0 , (sizePage != null) ? sizePage : 100, count, list);
	}

	@QueryParam("dataColeta")
	private Date dataColeta;

	@QueryParam("distribuidora")
	private Long distribuidora;

	@QueryParam("regiao")
	private RegiaoEnum regiao;

	public Date getDataColeta() {
		return dataColeta;
	}

	public void setDataColeta(Date dataColeta) {
		this.dataColeta = dataColeta;
	}

	public Long getDistribuidora() {
		return distribuidora;
	}

	public void setDistribuidora(Long distribuidora) {
		this.distribuidora = distribuidora;
	}

	public RegiaoEnum getRegiao() {
		return regiao;
	}

	public void setRegiao(RegiaoEnum regiao) {
		this.regiao = regiao;
	}

}