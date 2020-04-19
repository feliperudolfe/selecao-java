package com.indracompany.comuns.converter;

import java.text.ParseException;

import javax.swing.text.MaskFormatter;
import javax.ws.rs.WebApplicationException;

/**
 * Conversor para serialização atributos em DTO do tipo data
 *
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   19 de abr de 2020
 */
public class CnpjSerializer extends AbstractStringSerializer {

	private static MaskFormatter formatter;

	static {

		try {

			formatter = new MaskFormatter("##.###.###/####-##");
			formatter.setValueContainsLiteralCharacters(Boolean.FALSE);

		} catch (ParseException e) {
			throw new WebApplicationException("Ocorreu um erro ao aplicar mascara String");
		}

	}

	@Override
	public MaskFormatter getFormatter() {
		return formatter;
	}

}