package com.indracompany.comuns.converter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * Conversor abstrato para serialização atributos do tipo date
 *
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   19 de abr de 2020
 */
public abstract class AbstractDateSerializer extends JsonSerializer<Date> {

	public abstract SimpleDateFormat getFormatter();

	@Override
	public void serialize(Date value, JsonGenerator generator, SerializerProvider provider) throws IOException {
		if (value == null) {
			generator.writeNull();
		} else {
			generator.writeString(getFormatter().format(value.getTime()));
		}
	}

}