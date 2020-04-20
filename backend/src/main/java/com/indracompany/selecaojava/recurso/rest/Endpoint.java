package com.indracompany.selecaojava.recurso.rest;

import org.springframework.http.ResponseEntity;

import com.indracompany.comuns.modelo.dto.Resposta;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   18 de abr de 2020
 */
public interface Endpoint {

	public default ResponseEntity<Resposta> criarResposta(Resposta resposta) {
		return ResponseEntity.ok(resposta);
	}

	public default ResponseEntity<Resposta> criarRespostaData(Object data) {
		Resposta resposta = new Resposta();
		resposta.setData(data);
		return ResponseEntity.ok(resposta);
	}
}