package com.indracompany.selecaojava.persistencia.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.indracompany.comuns.persistencia.dao.DAO;
import com.indracompany.comuns.util.ObjectUtil;
import com.indracompany.comuns.util.PaginatorUtil;
import com.indracompany.selecaojava.persistencia.modelo.dto.VendaDTO;
import com.indracompany.selecaojava.persistencia.modelo.dto.VendaPaginadorDTO;
import com.indracompany.selecaojava.persistencia.modelo.entidade.Venda;

@Repository
public class VendaDAO extends DAO {

	private static final String CODIGO = "codigo";
	private static final String DATA_COLETA = "dataColeta";
	private static final String VALOR_VENDA = "valorVenda";
	private static final String VALOR_COMPRA = "valorCompra";
	private static final String PRODUTO = "produto";
	private static final String DISTRIBUIDORA = "distribuidora";

	/**
	 * Obter registros de forma paginada
	 *
	 * @author 	Felipe Rudolfe Carvalho Pinheiro
	 * @since   19 de abr de 2020
	 * @param 	paginator
	 */
	public VendaPaginadorDTO listar(VendaPaginadorDTO paginator) {

		CriteriaBuilder builder = getCriteriaBuilder();
		CriteriaQuery<VendaDTO> query = builder.createQuery(VendaDTO.class);
		Root<Venda> root = query.from(Venda.class);

		query.select(builder.construct(VendaDTO.class,
				root.get(CODIGO),
				root.get(DATA_COLETA),
				root.get(VALOR_VENDA),
				root.get(VALOR_COMPRA),
				root.get(PRODUTO),
				root.get(DISTRIBUIDORA)));

		List<Predicate> predicados = construirPredicados(builder, root, paginator);
		if (!predicados.isEmpty()) {
			query.where(predicados.toArray(new Predicate[predicados.size()]));
		}

		query.orderBy(builder.desc(root.get(DATA_COLETA)));

		List<VendaDTO> result = getEntityManager()
				.createQuery(query)
				.setFirstResult(PaginatorUtil.calcularIndiceInicial(paginator))
				.setMaxResults(PaginatorUtil.calcularIndiceFinal(paginator))
				.getResultList();

		paginator.setList(result);
		paginator.setCount(this.countPorFiltros(paginator));

		return paginator;
	}

	private Long countPorFiltros(VendaPaginadorDTO paginadorDTO) {

        CriteriaBuilder builder = getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Venda> root = query.from(Venda.class);
        List<Predicate> predicados = this.construirPredicados(builder, root, paginadorDTO);

        query.select(builder.count(root));
        if (!predicados.isEmpty()) {
        	query.where(predicados.toArray(new Predicate[predicados.size()]));
        }

        return getEntityManager().createQuery(query).getSingleResult().longValue();
	}

	private List<Predicate> construirPredicados(CriteriaBuilder builder, Root<Venda> root,
			VendaPaginadorDTO paginadorDTO) {

        List<Predicate> predicados = new ArrayList<>();
        if (ObjectUtil.isNotNull(paginadorDTO.getDataColeta())) {
        	predicados.add(builder.equal(root.get(DATA_COLETA), paginadorDTO.getDataColeta()));
        }

        return predicados;
    }

}