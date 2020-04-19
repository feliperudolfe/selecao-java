package com.indracompany.selecaojava.persistencia.modelo.mapper;

import java.util.List;

import org.springframework.util.Assert;

import com.indracompany.selecaojava.app.mensagem.Msg;
import com.indracompany.selecaojava.app.mensagem.MsgEnum;
import com.indracompany.selecaojava.persistencia.modelo.entidade.Estado;
import com.indracompany.selecaojava.persistencia.modelo.tipo.RegiaoEnum;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   18 de abr de 2020
 */
public class EstadoMapper {

	private static final int REGIAO_LINHA = 0;
	private static final int SIGLA_ESTADO_LINHA = 1;

	private EstadoMapper(){}

	public static Estado toEntidade(int cont, List<String> linha) {

		Estado retorno = new Estado();
		retorno.setCodigo(linha.get(SIGLA_ESTADO_LINHA));
		retorno.setRegiao(RegiaoEnum.getPorSigla(linha.get(REGIAO_LINHA)));

		Assert.notNull(retorno.getCodigo(), Msg.get(MsgEnum.MSG_IMP_CSV, cont, "Código UF"));
		Assert.notNull(retorno.getRegiao(), Msg.get(MsgEnum.MSG_IMP_CSV, cont, "Região UF"));

		return retorno;
	}

}