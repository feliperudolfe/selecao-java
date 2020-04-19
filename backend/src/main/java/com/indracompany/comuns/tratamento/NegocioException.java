package com.indracompany.comuns.tratamento;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.WebApplicationException;

import com.indracompany.comuns.modelo.dto.Mensagem;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   18 de abr de 2020
 */
public class NegocioException extends WebApplicationException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private List<Mensagem> mensagens;

	public NegocioException(Mensagem... mensagens) {
		this.mensagens = new ArrayList<>();
		for (Mensagem mensagem : mensagens) {
			this.mensagens.add(mensagem);
		}
	}

	public NegocioException(String tipo, String texto) {
		this.mensagens = new ArrayList<>();
		this.mensagens.add(new Mensagem(tipo, texto));
	}

	public NegocioException(String texto) {
		this.mensagens = new ArrayList<>();
		this.mensagens.add(new Mensagem(Mensagem.ERRO, texto));
	}

	public NegocioException(List<String> textos) {
		this.mensagens = new ArrayList<>();
		for (String texto : textos) {
			this.mensagens.add(new Mensagem(Mensagem.ERRO, texto));
		}
	}

	public List<Mensagem> getMensagens() {
		return mensagens;
	}

}