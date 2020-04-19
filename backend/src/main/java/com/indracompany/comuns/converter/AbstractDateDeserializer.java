package com.indracompany.comuns.converter;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.BadRequestException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * Conversor abstrato para deserialização atributos date
 *
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   19 de abr de 2020
 */
public abstract class AbstractDateDeserializer extends JsonDeserializer<Date> {

	public abstract SimpleDateFormat getFormatter();

	@Override
	public Date deserialize(JsonParser jsonparser, DeserializationContext context) throws IOException {

		String dateAsString = jsonparser.getText();
		try {

			return getFormatter().parse(dateAsString);

		} catch (ParseException e) {
			throw new BadRequestException("Formato inválido para data");
		}
	}

}