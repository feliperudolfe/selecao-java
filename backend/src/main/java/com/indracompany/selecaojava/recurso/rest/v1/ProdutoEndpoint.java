package com.indracompany.selecaojava.recurso.rest.v1;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.indracompany.selecaojava.persistencia.modelo.dto.OptionDTO;
import com.indracompany.selecaojava.recurso.rest.Endpoint;
import com.indracompany.selecaojava.servico.ProdutoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   19 de abr de 2020
 */
@CrossOrigin
@RestController
@RequestMapping("/produtos")
@Api(value = "Produtos")
public class ProdutoEndpoint implements Endpoint {

	@Autowired
	private ProdutoService service;

	@GetMapping(path = "/options", produces = MediaType.APPLICATION_JSON)
	@ApiOperation(
		value = "Listar produtos no formato options",
		notes = "Listar produtos no formato options do sistema")
    @ApiResponses(value = {
		@ApiResponse(
			code = 200,
			message = "Success"),
		@ApiResponse(
			code = 400,
			message = "Bad request"),
		@ApiResponse(
				code = 401,
				message = "Unauthorized"),
		@ApiResponse(
			code = 404,
			message = "Not found"),
    	@ApiResponse(
			code = 500,
			message = "Internal error")
    })
	public List<OptionDTO> listarOptions() {
		return this.service.listarOptions();
	}

}