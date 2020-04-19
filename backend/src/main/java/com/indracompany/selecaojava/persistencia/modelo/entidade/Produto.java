package com.indracompany.selecaojava.persistencia.modelo.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.indracompany.comuns.modelo.entidade.Entidade;
import com.indracompany.selecaojava.persistencia.modelo.tipo.UnidadeMedidaEnum;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   18 de abr de 2020
 */
@Entity
@Table(name = "tb_produtos")
public class Produto extends Entidade<Long> {

	/**
	 *
	 */
	private static final long serialVersionUID = -7075644099441254178L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@Column(name = "nome", nullable = false, length = 255)
	private String nome;

	@Enumerated(EnumType.STRING)
	@Column(name = "unidade_medida", nullable = false)
	private UnidadeMedidaEnum unidadeMedida;

	@ManyToOne
	@JoinColumn(name = "bandeira", nullable = false)
	private Bandeira bandeira;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public UnidadeMedidaEnum getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(UnidadeMedidaEnum unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

	public Bandeira getBandeira() {
		return bandeira;
	}

	public void setBandeira(Bandeira bandeira) {
		this.bandeira = bandeira;
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
		Produto other = (Produto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo)) {
			return false;
		}
		return true;
	}

}