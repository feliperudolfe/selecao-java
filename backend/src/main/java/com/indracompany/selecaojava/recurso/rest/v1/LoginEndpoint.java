package com.indracompany.selecaojava.recurso.rest.v1;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.indracompany.comuns.modelo.dto.Resposta;
import com.indracompany.selecaojava.persistencia.modelo.dto.CredentialsDTO;
import com.indracompany.selecaojava.recurso.rest.Endpoint;
import com.indracompany.selecaojava.servico.UsuarioService;

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
@RequestMapping("/login")
@Api(value = "Login de usuário")
public class LoginEndpoint implements Endpoint {

	@Autowired
	private UsuarioService service;

	@PostMapping
	@ApiOperation(
			value = "Login do usuário",
			notes = "Login do usuário no sistema")
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
	public ResponseEntity<Resposta> efetuarLogin(
			@ApiParam(value = "Credentials", required = true)
			@NotNull @Valid @RequestBody CredentialsDTO credentials,
			@Context HttpServletResponse servletResponse) {

		String token = this.service.efetuarLogin(credentials.getUsername(), credentials.getPassword());
		servletResponse.setHeader(HttpHeaders.SET_COOKIE, token);

		return criarRespostaData(Boolean.TRUE);
	}

}