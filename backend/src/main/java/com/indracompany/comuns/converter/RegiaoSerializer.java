package com.indracompany.comuns.converter;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.indracompany.selecaojava.persistencia.modelo.tipo.RegiaoEnum;

/**
 * Conversor abstrato para serialização atributos do tipo date
 *
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   19 de abr de 2020
 */
public class RegiaoSerializer extends JsonSerializer<RegiaoEnum> {

	@Override
	public void serialize(RegiaoEnum value, JsonGenerator generator, SerializerProvider provider) throws IOException {
		if (value == null) {
			generator.writeNull();
		} else {
			generator.writeString(value.getNome());
		}
	}

}