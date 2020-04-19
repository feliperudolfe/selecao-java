package com.indracompany.comuns.modelo.entidade;

import java.io.Serializable;

import com.indracompany.comuns.converter.Converter;
import com.indracompany.comuns.modelo.dto.DTO;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   18 de abr de 2020
 */
public abstract class Entidade<T> implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public abstract T getCodigo();

	public abstract void setCodigo(T codigo);

	/**
	 * Converte entidate em DTO referenciado, para isso a estrutura entre classes deve ser semelhante
	 *
	 * @author 	Felipe Rudolfe Carvalho Pinheiro
	 * @since   19 de abr de 2020
	 * @param 	<D>
	 * @param 	classe
	 */
	public <D extends DTO> D toDTO(Class<D> classe) {
    	return Converter.converter(this, classe);
    }
}