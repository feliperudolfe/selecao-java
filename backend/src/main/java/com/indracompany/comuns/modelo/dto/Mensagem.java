package com.indracompany.comuns.modelo.dto;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   18 de abr de 2020
 */
public class Mensagem {

	public static final String SUCESSO = "success";
	public static final String ERRO = "error";
	public static final String ALERTA = "warning";

	private String type;
	private String text;

	public Mensagem(String type, String text) {
		this.type = type;
		this.text = text;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}