package com.indracompany.selecaojava.servico.impl;

import javax.persistence.PersistenceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indracompany.comuns.tratamento.NegocioException;
import com.indracompany.selecaojava.app.mensagem.Msg;
import com.indracompany.selecaojava.app.mensagem.MsgEnum;
import com.indracompany.selecaojava.persistencia.modelo.entidade.Estado;
import com.indracompany.selecaojava.persistencia.modelo.entidade.Municipio;
import com.indracompany.selecaojava.persistencia.repositorio.MunicipioRepository;
import com.indracompany.selecaojava.servico.EstadoService;
import com.indracompany.selecaojava.servico.MunicipioService;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   18 de abr de 2020
 */
@Service
public class MunicipioServiceImpl implements MunicipioService {

	private static final Logger LOG = LoggerFactory.getLogger(MunicipioServiceImpl.class);

	private final MunicipioRepository repository;

	private final EstadoService estadoService;

	@Autowired
	public MunicipioServiceImpl(MunicipioRepository repository, EstadoService estadoService) {
		this.repository = repository;
		this.estadoService = estadoService;
	}

	@Override
	public Municipio salvar(Municipio municipio) {

		Municipio retorno = null;
		try {

			Estado uf = this.estadoService.salvar(municipio.getUf());
			municipio.setUf(uf);

			retorno = this.repository.findByNome(municipio.getNome());
			if (retorno == null) {
				retorno = this.repository.save(municipio);
			}

		} catch (PersistenceException e) {
			LOG.error(e.getMessage(), e);
			throw new NegocioException(Msg.get(MsgEnum.MSG_ERRO_PADRAO));
		}

		return retorno;
	}

}