package com.indracompany.comuns.modelo.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.indracompany.comuns.converter.Converter;
import com.indracompany.comuns.modelo.entidade.Entidade;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   19 de abr de 2020
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class DTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public <T extends Entidade<?>> T  toEntity(Class<T> classe) {
    	return Converter.converter(this, classe);
    }

}