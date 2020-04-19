package com.indracompany.selecaojava.recurso.rest.v1;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.indracompany.comuns.modelo.dto.Mensagem;
import com.indracompany.comuns.modelo.dto.Resposta;
import com.indracompany.selecaojava.app.mensagem.Msg;
import com.indracompany.selecaojava.app.mensagem.MsgEnum;
import com.indracompany.selecaojava.persistencia.modelo.dto.VendaDTO;
import com.indracompany.selecaojava.recurso.rest.Endpoint;
import com.indracompany.selecaojava.servico.VendaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

	@GetMapping(path = "/{codigo:[1-9][0-9]*}", produces = MediaType.APPLICATION_JSON)
	@ApiOperation(
		value = "Obter venda por código",
		notes = "Obter venda por código do sistema")
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
	public ResponseEntity<Resposta> get(
			@ApiParam(value = "Código", required = true)
			@Valid @NotNull @PathVariable("codigo") Long codigo) {

		VendaDTO venda = this.service.obterPorCodigo(codigo);

		return criarResposta(new Resposta(venda));
	}

	@PutMapping(path = "/{codigo:[1-9][0-9]*}")
	@ApiOperation(
		value = "Deletar venda por código",
		notes = "Deletar venda por código do sistema")
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
	public ResponseEntity<Resposta> remover(
			@ApiParam(value = "Código", required = true)
			@Valid @NotNull @PathVariable("codigo") Long codigo,
			@ApiParam(value = "Venda", required = true)
			@Valid @NotNull @RequestBody VendaDTO vendaDTO) {

		this.service.atualizar(codigo, vendaDTO);

		return criarResposta(new Resposta(Mensagem.SUCESSO, Msg.get(MsgEnum.MSG_SUCESSO)));
	}

	@DeleteMapping(path = "/{codigo:[1-9][0-9]*}")
	@ApiOperation(
		value = "Deletar venda por código",
		notes = "Deletar venda por código do sistema")
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
	public ResponseEntity<Resposta> remover(
			@ApiParam(value = "Código", required = true)
			@Valid @NotNull @PathVariable("codigo") Long codigo) {

		this.service.remover(codigo);

		return criarResposta(new Resposta(Mensagem.SUCESSO, Msg.get(MsgEnum.MSG_SUCESSO)));
	}

}