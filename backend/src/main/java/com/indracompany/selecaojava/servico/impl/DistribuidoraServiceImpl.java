package com.indracompany.selecaojava.servico.impl;

import javax.persistence.PersistenceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indracompany.comuns.tratamento.NegocioException;
import com.indracompany.selecaojava.app.mensagem.Msg;
import com.indracompany.selecaojava.app.mensagem.MsgEnum;
import com.indracompany.selecaojava.persistencia.modelo.entidade.Distribuidora;
import com.indracompany.selecaojava.persistencia.modelo.entidade.Municipio;
import com.indracompany.selecaojava.persistencia.repositorio.DistribuidoraRepository;
import com.indracompany.selecaojava.servico.DistribuidoraService;
import com.indracompany.selecaojava.servico.MunicipioService;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   18 de abr de 2020
 */
@Service
public class DistribuidoraServiceImpl implements DistribuidoraService {

	private static final Logger LOG = LoggerFactory.getLogger(DistribuidoraServiceImpl.class);

	@Autowired
	private DistribuidoraRepository repository;

	@Autowired
	private MunicipioService municipioService;

	@Override
	public Distribuidora salvar(Distribuidora distribuidora) {

		Distribuidora retorno = null;
		try {

			Municipio municipio = this.municipioService.salvar(distribuidora.getMunicipio());
			distribuidora.setMunicipio(municipio);

			retorno = this.repository.findByCnpj(distribuidora.getCnpj());
			if (retorno == null) {
				retorno = this.repository.save(distribuidora);
			}

		} catch (PersistenceException e) {
			LOG.error(e.getMessage(), e);
			throw new NegocioException(Msg.get(MsgEnum.MSG_ERRO_PADRAO));
		}

		return retorno;
	}

}