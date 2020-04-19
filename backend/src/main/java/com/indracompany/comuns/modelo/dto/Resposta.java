package com.indracompany.comuns.modelo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   18 de abr de 2020
 */
public class Resposta implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 731446072272793123L;

	private Object data;
	private List<Mensagem> mensagens;

	public Resposta(Object data, Mensagem ... mensagens) {
		this.data = data;
		this.mensagens = new ArrayList<>();
		for (Mensagem msg : mensagens) {
			this.mensagens.add(msg);
		}
	}

	public Resposta(Mensagem ... mensagens) {
		this.mensagens = new ArrayList<>();
		for (Mensagem msg : mensagens) {
			this.mensagens.add(msg);
		}
	}

	public Resposta(Object data, List<Mensagem> mensagens) {
		this.data = data;
		this.mensagens = new ArrayList<>();
		for (Mensagem msg : mensagens) {
			this.mensagens.add(msg);
		}
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public List<Mensagem> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<Mensagem> mensagens) {
		this.mensagens = mensagens;
	}

	public void addMensagem(Mensagem mensagem) {
		if (this.mensagens == null) {
			this.mensagens = new ArrayList<>();
		}
		this.mensagens.add(mensagem);
	}

}