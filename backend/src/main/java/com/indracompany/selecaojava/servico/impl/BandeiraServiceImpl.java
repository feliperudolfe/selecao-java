package com.indracompany.selecaojava.servico.impl;

import javax.persistence.PersistenceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indracompany.comuns.tratamento.NegocioException;
import com.indracompany.selecaojava.app.mensagem.Msg;
import com.indracompany.selecaojava.app.mensagem.MsgEnum;
import com.indracompany.selecaojava.persistencia.modelo.entidade.Bandeira;
import com.indracompany.selecaojava.persistencia.repositorio.BandeiraRepository;
import com.indracompany.selecaojava.servico.BandeiraService;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   18 de abr de 2020
 */
@Service
public class BandeiraServiceImpl implements BandeiraService {

	private static final Logger LOG = LoggerFactory.getLogger(BandeiraServiceImpl.class);

	private final BandeiraRepository repository;

	@Autowired
	public BandeiraServiceImpl(BandeiraRepository repository) {
		this.repository = repository;
	}

	@Override
	public Bandeira salvar(Bandeira bandeira) {

		Bandeira retorno = null;
		try {

			retorno = this.repository.findByNome(bandeira.getNome());
			if (retorno == null) {
				retorno = this.repository.save(bandeira);
			}

		} catch (PersistenceException e) {
			LOG.error(e.getMessage(), e);
			throw new NegocioException(Msg.get(MsgEnum.MSG_ERRO_PADRAO));
		}

		return retorno;
	}

}