package com.indracompany.comuns.modelo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

import com.indracompany.comuns.util.Base64Util;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   20 de abr de 2020
 */
public class Base64CsvMultipartFile implements MultipartFile {

	private String base64;

	public Base64CsvMultipartFile(String base64) {
		this.base64 = base64;
	}

	@Override
	public void transferTo(File dest) throws IOException, IllegalStateException {
		throw new IllegalStateException("Recurso não implementado em classe 'Base64CsvMultipartFile'");
	}

	@Override
	public boolean isEmpty() {
		return base64 != null;
	}

	@Override
	public long getSize() {
		return base64.length();
	}

	@Override
	public String getOriginalFilename() {
		return "arquivo.csv";
	}

	@Override
	public String getName() {
		return "arquivo";
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return Base64Util.converterEmInputStream(base64);
	}

	@Override
	public String getContentType() {
		return "application/vnd.ms-excel";
	}

	@Override
	public byte[] getBytes() throws IOException {
		return base64.getBytes();
	}
}