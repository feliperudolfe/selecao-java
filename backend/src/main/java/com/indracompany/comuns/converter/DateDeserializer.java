package com.indracompany.comuns.converter;

import java.text.SimpleDateFormat;

/**
 * Conversor para deserialização atributos em DTO do tipo data
 *
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   19 de abr de 2020
 */
public class DateDeserializer extends AbstractDateDeserializer {

	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public SimpleDateFormat getFormatter() {
		return this.formatter;
	}

}