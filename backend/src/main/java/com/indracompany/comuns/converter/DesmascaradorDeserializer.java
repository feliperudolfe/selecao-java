package com.indracompany.comuns.converter;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * Conversor abstrato para deserialização atributos date
 *
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   19 de abr de 2020
 */
public class DesmascaradorDeserializer extends JsonDeserializer<String> {

	@Override
	public String deserialize(JsonParser jsonparser, DeserializationContext context) throws IOException {

		String value = jsonparser.getText();
		if (value != null) {
			value = value.replaceAll("\\D", "");
		}

		return value;
	}

}