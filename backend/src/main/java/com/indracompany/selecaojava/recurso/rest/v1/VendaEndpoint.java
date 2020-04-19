package com.indracompany.selecaojava.recurso.rest.v1;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.indracompany.comuns.modelo.dto.Mensagem;
import com.indracompany.comuns.modelo.dto.Resposta;
import com.indracompany.selecaojava.app.mensagem.Msg;
import com.indracompany.selecaojava.app.mensagem.MsgEnum;
import com.indracompany.selecaojava.recurso.rest.Endpoint;
import com.indracompany.selecaojava.servico.VendaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   18 de abr de 2020
 */
@CrossOrigin
@RestController
@RequestMapping("/vendas")
@Api(value = "Vendas")
public class VendaEndpoint implements Endpoint {

	@Autowired
	private VendaService service;

	@DeleteMapping(path = "/{codigo:[1-9][0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(
		value = "Get authenticated user",
		notes = "Get authenticated user in the system")
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
	public ResponseEntity<Resposta> remover(@Valid @NotNull @PathVariable("codigo") Long codigo) {

		this.service.remover(codigo);

		return criarResposta(new Resposta(Mensagem.SUCESSO, Msg.get(MsgEnum.MSG_SUCESSO)));
	}

}