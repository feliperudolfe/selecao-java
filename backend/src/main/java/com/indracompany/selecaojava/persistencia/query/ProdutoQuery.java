package com.indracompany.selecaojava.persistencia.query;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   19 de abr de 2020
 */
public interface ProdutoQuery {

	String LIST_OPTIONS = "SELECT new com.indracompany.selecaojava.persistencia.modelo.dto.OptionDTO(p.codigo, p.nome, p.bandeira.nome) FROM Produto p";

}