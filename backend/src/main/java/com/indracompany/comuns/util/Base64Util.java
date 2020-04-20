package com.indracompany.comuns.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Base64;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   18 de abr de 2020
 */
public class Base64Util {

	private static final String BASE64_SUF = "data:";
	private static final String BASE64_SUF_CONT = ";base64,";

	private Base64Util() {}

	public static boolean isValid(final String base64) {

		Boolean retorno = Boolean.FALSE;
		if (ObjectUtil.isNotNullOrEmpty(base64) && base64.startsWith(BASE64_SUF) && base64.contains(BASE64_SUF_CONT)) {
			retorno = Boolean.TRUE;
		}

		return retorno;
	}

	public static InputStream converterEmInputStream(final String base64) {

		InputStream retorno = null;
		if (ObjectUtil.isNotNullOrEmpty(base64)) {
			String[] part = base64.split(",");
			retorno = new ByteArrayInputStream(Base64.getDecoder().decode(part[1]));
		}

		return retorno;
	}

}