package com.indracompany.comuns.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.WebApplicationException;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   18 de abr de 2020
 */
public class DataUtil {

	private DataUtil() {}

	public static Date toDate(String data, String formato) {

		Date retorno = null;
		try {

			if (data != null && !"".equals(data)) {

				SimpleDateFormat formatter = new SimpleDateFormat(formato);
				retorno = formatter.parse(data);
			}

		} catch (Exception e) {
			throw new WebApplicationException("Ocorreu um erro ao converter data");
		}

		return retorno;
	}

}