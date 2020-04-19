package com.indracompany.selecaojava.persistencia.modelo.tipo;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   18 de abr de 2020
 */
public enum UnidadeMedidaEnum {

	VALOR_LITRO("R$ / litro"),
	;

	private String nome;

	private UnidadeMedidaEnum(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public static UnidadeMedidaEnum getPorNome(String nome) {

		UnidadeMedidaEnum retorno = null;
		if (nome != null) {
			nome = nome.trim();
			for (UnidadeMedidaEnum item : UnidadeMedidaEnum.values()) {
				if (item.getNome().equalsIgnoreCase(nome)) {
					retorno = item;
					break;
				}
			}
		}

		return retorno;
	}

}