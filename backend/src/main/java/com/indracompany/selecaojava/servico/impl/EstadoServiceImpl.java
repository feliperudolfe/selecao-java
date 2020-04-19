package com.indracompany.selecaojava.servico.impl;

import javax.persistence.PersistenceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indracompany.comuns.tratamento.NegocioException;
import com.indracompany.selecaojava.app.mensagem.Msg;
import com.indracompany.selecaojava.app.mensagem.MsgEnum;
import com.indracompany.selecaojava.persistencia.dao.repositorio.EstadoRepository;
import com.indracompany.selecaojava.persistencia.modelo.entidade.Estado;
import com.indracompany.selecaojava.servico.EstadoService;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   18 de abr de 2020
 */
@Service
public class EstadoServiceImpl implements EstadoService {

	private static final Logger LOG = LoggerFactory.getLogger(EstadoServiceImpl.class);

	@Autowired
	private EstadoRepository repository;

	@Override
	public Estado salvar(Estado estado) {

		Estado retorno = null;
		try {

			retorno = this.repository.findByCodigo(estado.getCodigo());
			if (retorno == null) {
				retorno = this.repository.save(estado);
			}

		} catch (PersistenceException e) {
			LOG.error(e.getMessage(), e);
			throw new NegocioException(Msg.get(MsgEnum.MSG_ERRO_PADRAO));
		}

		return retorno;
	}

}