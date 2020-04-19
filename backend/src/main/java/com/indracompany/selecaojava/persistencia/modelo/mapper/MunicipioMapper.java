package com.indracompany.selecaojava.persistencia.modelo.mapper;

import java.util.List;

import org.springframework.util.Assert;

import com.indracompany.selecaojava.app.mensagem.Msg;
import com.indracompany.selecaojava.app.mensagem.MsgEnum;
import com.indracompany.selecaojava.persistencia.modelo.entidade.Municipio;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   18 de abr de 2020
 */
public class MunicipioMapper {

	private static final int NOME_MUNICIPIO_LINHA = 2;

	private MunicipioMapper(){}

	public static Municipio toEntidade(int cont, List<String> linha) {

		Municipio retorno = new Municipio();
		retorno.setNome(linha.get(NOME_MUNICIPIO_LINHA));
		retorno.setUf(EstadoMapper.toEntidade(cont, linha));

		Assert.notNull(retorno.getNome(), Msg.getMessage(MsgEnum.MSG_IMP_CSV, cont, "Nome do município"));
		Assert.notNull(retorno.getUf(), Msg.getMessage(MsgEnum.MSG_IMP_CSV, cont, "UF"));

		return retorno;
	}

}