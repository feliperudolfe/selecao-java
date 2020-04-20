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
	private List<Mensagem> messages;

	public Resposta() {	}

	public Resposta(List<Mensagem> mensagens) {
		this.messages = mensagens;
	}

	public Resposta(Mensagem ... mensagens) {
		this.messages = new ArrayList<>();
		for (Mensagem msg : mensagens) {
			this.messages.add(msg);
		}
	}

	public Resposta(Object data, List<Mensagem> mensagens) {
		this.data = data;
		this.messages = new ArrayList<>();
		for (Mensagem msg : mensagens) {
			this.messages.add(msg);
		}
	}

	public Resposta(String tipo, String texto) {
		this.messages = new ArrayList<>();
		this.messages.add(new Mensagem(tipo, texto));
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public List<Mensagem> getMessages() {
		return messages;
	}

	public void setMessages(List<Mensagem> mensagens) {
		this.messages = mensagens;
	}

	public void addMessage(Mensagem mensagem) {
		if (this.messages == null) {
			this.messages = new ArrayList<>();
		}
		this.messages.add(mensagem);
	}

}