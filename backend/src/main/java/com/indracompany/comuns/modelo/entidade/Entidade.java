package com.indracompany.comuns.modelo.entidade;

import java.io.Serializable;

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

}