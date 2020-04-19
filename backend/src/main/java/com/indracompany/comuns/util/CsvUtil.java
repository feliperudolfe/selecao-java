package com.indracompany.comuns.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   18 de abr de 2020
 */
public class CsvUtil {

	private static final String SEPARATOR = "\t";

	private CsvUtil() {}

	public static List<List<String>> ler(MultipartFile file) throws IOException {

		List<List<String>> retorno = new ArrayList<>();

		InputStreamReader isr = new InputStreamReader(file.getInputStream(), "UTF-16");
		BufferedReader arquivoBuffer = new BufferedReader(isr);

		String header = getheader(arquivoBuffer).toUpperCase();
		String[] keys = header.split(SEPARATOR);
		for (int i = 0; i < keys.length; i++) {
			keys[i] = keys[i].trim();
		}

		while (arquivoBuffer.ready()) {

			String line = getLine(arquivoBuffer);
			if ("".equals(line)) {
				continue;
			}

			String[] values = line.split(SEPARATOR);
			List<String> linha = Arrays.asList(values);
			retorno.add(linha);
		}

		arquivoBuffer.close();

		return retorno;
	}

	private static String getheader(BufferedReader arquivoBuffer) throws IOException {

		String retorno = "";
		if (arquivoBuffer.ready()) {
			retorno += SEPARATOR + arquivoBuffer.readLine();
		}

		return retorno;
	}

	private static String getLine(BufferedReader arquivoBuffer) throws IOException {

		String retorno = "";
		String lineValue = arquivoBuffer.readLine();

		if (lineValue != null) {
			retorno += lineValue;
		}

		return retorno.trim();
	}
}