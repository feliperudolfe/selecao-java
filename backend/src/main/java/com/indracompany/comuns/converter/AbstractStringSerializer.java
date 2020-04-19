package com.indracompany.comuns.converter;

import java.io.IOException;
import java.text.ParseException;

import javax.swing.text.MaskFormatter;
import javax.ws.rs.BadRequestException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * Conversor abstrato para serialização atributos do tipo date
 *
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   19 de abr de 2020
 */
public abstract class AbstractStringSerializer extends JsonSerializer<String> {

	public abstract MaskFormatter getFormatter() throws ParseException;

	@Override
	public void serialize(String value, JsonGenerator generator, SerializerProvider provider) throws IOException {
		try {
			if (value == null) {
				generator.writeNull();
			} else {
				generator.writeString(getFormatter().valueToString(value));
			}
		} catch (IOException | ParseException e) {
			throw new BadRequestException("Ocorreu um erro ao aplicar mascara.");
		}
	}

}