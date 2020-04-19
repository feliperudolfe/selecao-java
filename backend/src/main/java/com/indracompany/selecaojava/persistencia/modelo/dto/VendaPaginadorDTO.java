package com.indracompany.selecaojava.persistencia.modelo.dto;

import java.util.Date;
import java.util.List;

import javax.ws.rs.QueryParam;

import com.indracompany.comuns.modelo.dto.PaginadorDTO;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   19 de abr de 2020
 */
public class VendaPaginadorDTO extends PaginadorDTO<VendaDTO> {

	/**
	 *
	 */
	private static final long serialVersionUID = -7823976973357387428L;

	public VendaPaginadorDTO(Integer currentPage, Integer sizePage, Date dataColeta) {
		super((currentPage != null) ? currentPage : 0 , (sizePage != null) ? sizePage : 10000);
		this.dataColeta = dataColeta;
	}

	public VendaPaginadorDTO(Integer currentPage, Integer sizePage, Long count, List<VendaDTO> list) {
		super((currentPage != null) ? currentPage : 0 , (sizePage != null) ? sizePage : 10000, count, list);
	}

	@QueryParam("dataColeta")
	private Date dataColeta;

	public Date getDataColeta() {
		return dataColeta;
	}

	public void setDataColeta(Date dataColeta) {
		this.dataColeta = dataColeta;
	}

}