package com.indracompany.comuns.converter;

import java.text.SimpleDateFormat;

/**
 * Conversor para serialização atributos em DTO do tipo data
 *
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   19 de abr de 2020
 */
public class DateSerializer extends AbstractDateSerializer {

	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public SimpleDateFormat getFormatter() {
		return this.formatter;
	}

}