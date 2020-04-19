package com.indracompany.selecaojava.recurso.rest.v1;

import java.util.Date;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.indracompany.selecaojava.persistencia.modelo.dto.VendaPaginadorDTO;
import com.indracompany.selecaojava.recurso.rest.Endpoint;
import com.indracompany.selecaojava.servico.VendaService;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   18 de abr de 2020
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/historico-precos")
public class HistoricoPrecosEndpoint implements Endpoint {

	@Autowired
	private VendaService service;

	@GetMapping()
	public VendaPaginadorDTO getlist(
			@DefaultValue("0")
			@QueryParam("currentPage")
			Integer currentPage,
			@DefaultValue("10")
			@QueryParam("sizePage")
			Integer sizePage,
			@DateTimeFormat(pattern = "dd/MM/yyyy")
			@QueryParam("dataColeta")
			Date dataColeta) {

		return this.service.listar(new VendaPaginadorDTO(currentPage, sizePage, dataColeta));
	}

}