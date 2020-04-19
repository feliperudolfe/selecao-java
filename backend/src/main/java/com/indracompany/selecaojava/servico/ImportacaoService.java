package com.indracompany.selecaojava.servico;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   18 de abr de 2020
 */
public interface ImportacaoService {

	/**
	 * Carregar arquivo de importação com informações sobre compras de combustível na base de dados
	 *
	 * @author 	Felipe Rudolfe Carvalho Pinheiro
	 * @since   18 de abr de 2020
	 * @param 	file
	 */
	void carregarArquivoCSV(MultipartFile file);
}