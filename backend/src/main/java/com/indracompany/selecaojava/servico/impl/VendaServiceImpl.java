package com.indracompany.selecaojava.servico.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.PersistenceException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indracompany.comuns.tratamento.NegocioException;
import com.indracompany.selecaojava.app.mensagem.Msg;
import com.indracompany.selecaojava.app.mensagem.MsgEnum;
import com.indracompany.selecaojava.persistencia.dao.VendaDAO;
import com.indracompany.selecaojava.persistencia.modelo.dto.VendaDTO;
import com.indracompany.selecaojava.persistencia.modelo.dto.VendaPaginadorDTO;
import com.indracompany.selecaojava.persistencia.modelo.entidade.Distribuidora;
import com.indracompany.selecaojava.persistencia.modelo.entidade.Produto;
import com.indracompany.selecaojava.persistencia.modelo.entidade.Venda;
import com.indracompany.selecaojava.persistencia.repositorio.VendaRepository;
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
	private VendaDAO dao;

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

	@Override
	public VendaPaginadorDTO listar(VendaPaginadorDTO paginator) {

		try {

			paginator = this.dao.listar(paginator);

		} catch (PersistenceException e) {
			LOG.error(e.getMessage(), e);
			throw new NegocioException(Msg.get(MsgEnum.MSG_ERRO_PADRAO));
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new NegocioException(Msg.get(MsgEnum.MSG_ERRO_PADRAO));
		}

		return paginator;
	}

	@Override
	public void remover(Long codigo) {

		try {

			this.repository.deleteById(codigo);

		} catch (PersistenceException e) {
			LOG.error(e.getMessage(), e);
			throw new NegocioException(Msg.get(MsgEnum.MSG_ERRO_PADRAO));
		}
	}

	@Override
	public VendaDTO obterPorCodigo(@Valid @NotNull Long codigo) {

		VendaDTO retorno = null;
		try {

			Optional<Venda> opt = this.repository.findById(codigo);
			if (!opt.isPresent()) {
				throw new NegocioException(Msg.get(MsgEnum.MSG_ERRO_PADRAO, "Venda"));
			}

			retorno = opt.get().toDTO(VendaDTO.class);

		} catch (PersistenceException e) {
			LOG.error(e.getMessage(), e);
			throw new NegocioException(Msg.get(MsgEnum.MSG_ERRO_PADRAO));
		}

		return retorno;
	}

	@Override
	public void atualizar(Long codigo, VendaDTO vendaDTO) {

		try {

			Optional<Venda> opt = this.repository.findById(codigo);
			if (!opt.isPresent()) {
				throw new NegocioException(Msg.get(MsgEnum.MSG_ERRO_PADRAO, "Venda"));
			}

			Produto produto = this.produtoService.consultarPorCodigo(vendaDTO.getProduto().getCodigo());
			Distribuidora distribuidora = this.distribuidoraService.consultarPorCodigo(vendaDTO.getDistribuidora().getCodigo());

			Venda vendaAtual = opt.get();
			vendaAtual.setDataColeta(vendaDTO.getDataColeta());
			vendaAtual.setValorVenda(vendaDTO.getValorVenda());
			vendaAtual.setValorCompra(vendaDTO.getValorCompra());
			vendaAtual.setProduto(produto);
			vendaAtual.setDistribuidora(distribuidora);

			this.repository.save(vendaAtual);

		} catch (PersistenceException e) {
			LOG.error(e.getMessage(), e);
			throw new NegocioException(Msg.get(MsgEnum.MSG_ERRO_PADRAO));
		}
	}

}