package com.indracompany.selecaojava.app.mensagem;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import com.indracompany.comuns.tratamento.NegocioException;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   18 de abr de 2020
 */
public class Msg {

	private static final String ARQ_PROP = "mensagem";

	private Msg() {}

	public static String get(MsgEnum chave) {
		return get(chave.toString());
	}

	public static String get(MsgEnum chave, Object ... parametros) {
		return get(chave.toString(), parametros);
	}

	public static String get(String chave, Object ... parametros) {

		String retorno = null;
		try {

			ResourceBundle bundle = ResourceBundle.getBundle(ARQ_PROP, Locale.getDefault());
			retorno = bundle.getString(chave);
			retorno = MessageFormat.format(retorno, parametros);

		} catch (Exception e) {
			throw new NegocioException("Mensagem not found");
		}

		return retorno;
	}
}