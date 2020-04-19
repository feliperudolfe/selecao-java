package com.indracompany.comuns.modelo.dto;

import java.io.Serializable;
import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   19 de abr de 2020
 */
public abstract class PaginadorDTO<D extends DTO> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public PaginadorDTO(Integer currentPage, Integer sizePage) {
		super();
		this.currentPage = currentPage;
		this.sizePage = sizePage;
	}

	public PaginadorDTO(Integer currentPage, Integer sizePage, Long count, List<D> list) {
		super();
		this.currentPage = currentPage;
		this.sizePage = sizePage;
		this.count = count;
		this.list = list;
	}

	@DefaultValue("0")
    @QueryParam("currentPage")
    private Integer currentPage = 0;

    @DefaultValue("10000")
    @QueryParam("sizePage")
    private Integer sizePage = 10;

    @QueryParam("count")
    private Long count;

    private List<D> list;

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getSizePage() {
		return sizePage;
	}

	public void setSizePage(Integer sizePage) {
		this.sizePage = sizePage;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public List<D> getList() {
		return list;
	}

	public void setList(List<D> list) {
		this.list = list;
	}

}