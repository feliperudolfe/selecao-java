package com.indracompany.selecaojava.persistencia.modelo.tipo;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   18 de abr de 2020
 */
public enum RegiaoEnum {

	N("N", "Norte"),
	S("S","Sul"),
	NE("NE", "Nordeste"),
	CO("CO", "Centro Oeste"),
	SE("SE", "Sudeste"),
	;

	private String sigla;
	private String nome;

	private RegiaoEnum(String sigla, String nome) {
		this.sigla = sigla;
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public String getNome() {
		return nome;
	}

	public static RegiaoEnum getPorSigla(String sigla) {

		RegiaoEnum retorno = null;
		if (sigla != null) {
			sigla = sigla.trim();
			for (RegiaoEnum item : RegiaoEnum.values()) {
				if (item.getSigla().equalsIgnoreCase(sigla)) {
					retorno = item;
					break;
				}
			}
		}

		return retorno;
	}

}