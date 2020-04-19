package com.indracompany.selecaojava.persistencia.modelo.entidade;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.indracompany.comuns.modelo.entidade.Entidade;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   18 de abr de 2020
 */
@Entity
@Table(name = "tb_distribuidoras")
public class Distribuidora extends Entidade<Long> {

	/**
	 *
	 */
	private static final long serialVersionUID = 7008573804913294590L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@Column(name = "cnpj", nullable = false, length = 14)
	private String cnpj;

	@Column(name = "nome", nullable = false, length = 255)
	private String nome;

	@ManyToOne
	@JoinColumn(name = "municipio", nullable = false)
	private Municipio municipio;

	@OneToMany(mappedBy = "distribuidora")
	private List<Venda> compras;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public List<Venda> getCompras() {
		return compras;
	}

	public void setCompras(List<Venda> compras) {
		this.compras = compras;
	}

	public void addProdutoCompra(Venda venda) {
		if (this.compras == null) {
			this.compras = new ArrayList<>();
		}
		this.compras.add(venda);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
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
		Distribuidora other = (Distribuidora) obj;
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj)) {
			return false;
		}
		return true;
	}

}