package com.indracompany.selecaojava.recurso.rest.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.indracompany.comuns.modelo.dto.Mensagem;
import com.indracompany.comuns.modelo.dto.Resposta;
import com.indracompany.selecaojava.app.mensagem.Msg;
import com.indracompany.selecaojava.app.mensagem.MsgEnum;
import com.indracompany.selecaojava.recurso.rest.Endpoint;
import com.indracompany.selecaojava.servico.ImportacaoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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

	@ApiOperation(value = "Carregar arquivo de importações no sistema")
	@PostMapping(path = "carregar")
	public ResponseEntity<Resposta> carregarArquivoCSV(@RequestParam("file") MultipartFile file) {

		this.service.carregarArquivoCSV(file);

		return criarResposta(new Resposta(new Mensagem(Mensagem.SUCESSO, Msg.getMessage(MsgEnum.MSG003))));
	}

}