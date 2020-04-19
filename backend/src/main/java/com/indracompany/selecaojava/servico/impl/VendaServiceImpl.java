package com.indracompany.selecaojava.servico.impl;

import java.util.List;

import javax.persistence.PersistenceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indracompany.comuns.tratamento.NegocioException;
import com.indracompany.selecaojava.app.mensagem.Msg;
import com.indracompany.selecaojava.app.mensagem.MsgEnum;
import com.indracompany.selecaojava.persistencia.dao.repositorio.VendaRepository;
import com.indracompany.selecaojava.persistencia.modelo.entidade.Distribuidora;
import com.indracompany.selecaojava.persistencia.modelo.entidade.Produto;
import com.indracompany.selecaojava.persistencia.modelo.entidade.Venda;
import com.indracompany.selecaojava.servico.DistribuidoraService;
import com.indracompany.selecaojava.servico.ProdutoService;
import com.indracompany.selecaojava.servico.VendaService;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   18 de abr de 2020
 */
@Service
public class VendaServiceImpl implements VendaService {

	private static final Logger LOG = LoggerFactory.getLogger(VendaServiceImpl.class);

	@Autowired
	private VendaRepository repository;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private DistribuidoraService distribuidoraService;

	@Override
	public Venda salvar(Venda venda) {

		Venda retorno = null;
		try {

			Produto produto = this.produtoService.salvar(venda.getProduto());
			Distribuidora distribuidora = this.distribuidoraService.salvar(venda.getDistribuidora());

			venda.setProduto(produto);
			venda.setDistribuidora(distribuidora);

			retorno = this.repository.save(venda);

		} catch (PersistenceException e) {
			LOG.error(e.getMessage(), e);
			throw new NegocioException(Msg.get(MsgEnum.MSG_ERRO_PADRAO));
		}

		return retorno;
	}

	@Override
	public void salvar(List<Venda> vendas) {

		try {

			LOG.info("Iniciando inserção de informações das vendas obtidas em arquivo CSV");

			for (Venda venda : vendas) {
				this.salvar(venda);
			}

			LOG.info("Vendas obtidas em arquivo CSV processadas e salvas com sucesso");

			// TODO Notificar usuário do fim do processamento

		} catch (NegocioException e) {
			LOG.info("Ocorreu um erro durante as inserções das informações das vendas obtidas em arquivo CSV");
			throw e;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new NegocioException(Msg.get(MsgEnum.MSG_ERRO_PADRAO));
		}
	}

}