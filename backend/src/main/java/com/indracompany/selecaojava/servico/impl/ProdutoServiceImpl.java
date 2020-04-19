package com.indracompany.selecaojava.servico.impl;

import javax.persistence.PersistenceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indracompany.comuns.tratamento.NegocioException;
import com.indracompany.selecaojava.app.mensagem.Msg;
import com.indracompany.selecaojava.app.mensagem.MsgEnum;
import com.indracompany.selecaojava.persistencia.dao.repositorio.ProdutoRepository;
import com.indracompany.selecaojava.persistencia.modelo.entidade.Bandeira;
import com.indracompany.selecaojava.persistencia.modelo.entidade.Produto;
import com.indracompany.selecaojava.servico.BandeiraService;
import com.indracompany.selecaojava.servico.ProdutoService;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   18 de abr de 2020
 */
@Service
public class ProdutoServiceImpl implements ProdutoService {

	private static final Logger LOG = LoggerFactory.getLogger(ProdutoServiceImpl.class);

	@Autowired
	private ProdutoRepository repository;

	@Autowired
	private BandeiraService bandeiraService;

	@Override
	public Produto salvar(Produto produto) {

		Produto retorno = null;
		try {

			Bandeira bandeira = this.bandeiraService.salvar(produto.getBandeira());
			produto.setBandeira(bandeira);

			retorno = this.repository.findByNomeAndBandeira(produto.getNome(), bandeira);
			if (retorno == null) {
				retorno = this.repository.save(produto);
			}

		} catch (PersistenceException e) {
			LOG.error(e.getMessage(), e);
			throw new NegocioException(Msg.getMessage(MsgEnum.MSG_ERRO_PADRAO));
		}

		return retorno;
	}

}