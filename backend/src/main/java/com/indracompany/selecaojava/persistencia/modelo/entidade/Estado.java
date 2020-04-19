package com.indracompany.selecaojava.persistencia.modelo.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.indracompany.comuns.modelo.entidade.Entidade;
import com.indracompany.selecaojava.persistencia.modelo.tipo.RegiaoEnum;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   18 de abr de 2020
 */
@Entity
@Table(name = "tb_estados")
public class Estado extends Entidade<String> {

	/**
	 *
	 */
	private static final long serialVersionUID = -6250232823116206371L;

	@Id
	private String codigo;

	@Enumerated(EnumType.STRING)
	@Column(name = "regiao", nullable = false)
	private RegiaoEnum regiao;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public RegiaoEnum getRegiao() {
		return regiao;
	}

	public void setRegiao(RegiaoEnum regiao) {
		this.regiao = regiao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estado other = (Estado) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo)) {
			return false;
		}
		return true;
	}

}