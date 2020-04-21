package com.indracompany.selecaojava.recurso.rest.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.indracompany.comuns.modelo.Base64CsvMultipartFile;
import com.indracompany.comuns.modelo.dto.Mensagem;
import com.indracompany.comuns.modelo.dto.Resposta;
import com.indracompany.selecaojava.app.mensagem.Msg;
import com.indracompany.selecaojava.app.mensagem.MsgEnum;
import com.indracompany.selecaojava.recurso.rest.Endpoint;
import com.indracompany.selecaojava.servico.ImportacaoService;

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
@RequestMapping("/importacoes")
@Api(value = "Importações")
public class ImportacaoEndpoint implements Endpoint {

	@Autowired
	private ImportacaoService service;

	@PostMapping("carregar")
	@ApiOperation(
		value = "Carregar arquivo de importações",
		notes = "Carregar arquivo de importações no sistema")
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
	public ResponseEntity<Resposta> carregarArquivoCSV(@RequestBody @RequestParam("file") MultipartFile file) {

		this.service.carregarArquivoCSV(file);

		return criarResposta(new Resposta(new Mensagem(Mensagem.SUCESSO, Msg.get(MsgEnum.MSG003))));
	}

	@PostMapping("carregar-base64")
	@ApiOperation(
		value = "Carregar arquivo de importações",
		notes = "Carregar arquivo de importações no sistema")
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
	public ResponseEntity<Resposta> carregarBase64CSV(@RequestParam("file") String base64) {

		System.out.println(base64);

		this.service.carregarArquivoCSV(new Base64CsvMultipartFile(base64));

		return criarResposta(new Resposta(new Mensagem(Mensagem.SUCESSO, Msg.get(MsgEnum.MSG003))));
	}

}