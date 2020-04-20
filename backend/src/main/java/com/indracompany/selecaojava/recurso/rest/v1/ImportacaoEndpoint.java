package com.indracompany.selecaojava.recurso.rest.v1;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.indracompany.comuns.modelo.dto.Mensagem;
import com.indracompany.comuns.modelo.dto.Resposta;
import com.indracompany.comuns.util.Base64Util;
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
	@PostMapping("carregar")
	public ResponseEntity<Resposta> carregarArquivoCSV(@RequestBody @RequestParam("file") MultipartFile file) {

		this.service.carregarArquivoCSV(file);

		return criarResposta(new Resposta(new Mensagem(Mensagem.SUCESSO, Msg.get(MsgEnum.MSG003))));
	}

	@ApiOperation(value = "Carregar arquivo de importações no sistema")
	@PostMapping("carregar-base64")
	public ResponseEntity<Resposta> carregarBase64CSV(@RequestParam("file") String base64) {

		this.service.carregarArquivoCSV(new MultipartFile() {

			@Override
			public void transferTo(File dest) throws IOException, IllegalStateException {
				// TODO Auto-generated method stub

			}

			@Override
			public boolean isEmpty() {
				return base64 != null;
			}

			@Override
			public long getSize() {
				return base64.length();
			}

			@Override
			public String getOriginalFilename() {
				return "arquivo.csv";
			}

			@Override
			public String getName() {
				return "arquivo";
			}

			@Override
			public InputStream getInputStream() throws IOException {
				return Base64Util.converterEmInputStream(base64);
			}

			@Override
			public String getContentType() {
				return "application/vnd.ms-excel";
			}

			@Override
			public byte[] getBytes() throws IOException {
				return base64.getBytes();
			}
		});

		return criarResposta(new Resposta(new Mensagem(Mensagem.SUCESSO, Msg.get(MsgEnum.MSG003))));
	}

}