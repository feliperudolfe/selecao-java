package com.indracompany.comuns.util;

import javax.ws.rs.WebApplicationException;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   18 de abr de 2020
 */
public class MonetarioUtil {

	private MonetarioUtil() {}

	public static Double toDouble(String value) {

		Double retorno = null;
		try {

			if (value != null && !"".equals(value)) {

				value = value.replace(",", ".");
				retorno = Double.parseDouble(value);
			}

		} catch (Exception e) {
			throw new WebApplicationException("Ocorreu um erro ao converter data");
		}

		return retorno;
	}

}