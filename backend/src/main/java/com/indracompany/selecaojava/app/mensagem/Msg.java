package com.indracompany.selecaojava.app.mensagem;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   18 de abr de 2020
 */
public class Msg {

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("mensagem", new Locale("pt", "BR"));

	private Msg() {}

	public static String get(MsgEnum msgEnum) {
		return new String(RESOURCE_BUNDLE.getString(msgEnum.toString()).getBytes());

	}

	public static String get(MsgEnum msgEnum, Object... args) {
		String mensagem = get(msgEnum);
		if (args.length > 0) {
			mensagem = MessageFormat.format(mensagem, args);
		}
		return mensagem;
	}

}
