package com.indracompany.selecaojava.persistencia.query;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   19 de abr de 2020
 */
public interface DistribuidoraQuery {

	String LIST_OPTIONS = "SELECT new com.indracompany.selecaojava.persistencia.modelo.dto.OptionDTO(d.codigo, d.cnpj, d.nome) FROM Distribuidora d";

}