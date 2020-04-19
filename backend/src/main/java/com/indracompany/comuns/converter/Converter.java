package com.indracompany.comuns.converter;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Converter {

	private static final ObjectMapper MAPPER;

	static {

		MAPPER = new ObjectMapper();
		MAPPER.setSerializationInclusion(Include.NON_NULL);
		MAPPER.setSerializationInclusion(Include.NON_EMPTY);
	}

	private Converter() {
		super();
	}

	public static <S, C> C converter(S objeto, Class<C> classe) {
		return MAPPER.convertValue(objeto, classe);
	}

}