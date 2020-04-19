package com.indracompany.selecaojava.recurso.rest.v1;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.indracompany.comuns.modelo.dto.Mensagem;
import com.indracompany.comuns.modelo.dto.Resposta;
import com.indracompany.selecaojava.app.mensagem.Msg;
import com.indracompany.selecaojava.app.mensagem.MsgEnum;
import com.indracompany.selecaojava.persistencia.modelo.dto.UserDTO;
import com.indracompany.selecaojava.persistencia.modelo.entidade.Usuario;
import com.indracompany.selecaojava.persistencia.modelo.mapper.UsuarioMapper;
import com.indracompany.selecaojava.recurso.rest.Endpoint;
import com.indracompany.selecaojava.servico.UsuarioService;
import com.indracompany.selecaojava.servico.impl.UsuarioServiceImpl;

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
@RequestMapping("/usuarios")
@Api(value = "Usuários")
public class UsuarioEndpoint implements Endpoint {

	@Autowired
	private UsuarioService service;

	@PostMapping
	@ApiOperation(
		value = "Registrar usuário",
		notes = "Registrar usuário no sistema")
    @ApiResponses(value = {
		@ApiResponse(
			code = 200,
			message = "Success"),
		@ApiResponse(
			code = 400,
			message = "Bad request"),
		@ApiResponse(
			code = 404,
			message = "Not found"),
    	@ApiResponse(
			code = 500,
			message = "Internal error")
    })
	public ResponseEntity<Resposta> cadastrar(
			@ApiParam(value = "User object that to be registered", required = true)
			@NotNull @Valid @RequestBody UserDTO userDTO) {

		Usuario user = UsuarioMapper.toEntity(userDTO);
		this.service.cadastrar(user);

		return criarResposta(new Resposta(Mensagem.SUCESSO, Msg.get(MsgEnum.MSG_SUCESSO)));
	}

	@GetMapping
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
	public ResponseEntity<Resposta> getUser(
			@ApiParam(value = "Authorization token", required = true)
			@RequestHeader(UsuarioServiceImpl.AUTHORRIZATION_HEADER_KEY) String token) {

		Usuario user = this.service.buscarPorToken(token);

		return criarResposta(new Resposta(UsuarioMapper.toDTO(user)));
	}

}