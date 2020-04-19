package com.indracompany.selecaojava.persistencia.modelo.mapper;

import java.util.List;

import org.springframework.util.Assert;

import com.indracompany.selecaojava.app.mensagem.Msg;
import com.indracompany.selecaojava.app.mensagem.MsgEnum;
import com.indracompany.selecaojava.persistencia.modelo.entidade.Distribuidora;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   18 de abr de 2020
 */
public class DistribuidoraMapper {

	private static final int NOME_REVENDEDORA_LINHA = 3;
	private static final int CNPJ_LINHA = 4;

	private DistribuidoraMapper(){}

	public static Distribuidora toEntidade(int cont, List<String> linha) {

		Distribuidora retorno = new Distribuidora();
		retorno.setCnpj(linha.get(CNPJ_LINHA));
		retorno.setNome(linha.get(NOME_REVENDEDORA_LINHA));
		retorno.setMunicipio(MunicipioMapper.toEntidade(cont, linha));

		Assert.notNull(retorno.getCnpj(), Msg.get(MsgEnum.MSG_IMP_CSV, cont, "CNJP da distribuidora"));
		Assert.notNull(retorno.getNome(), Msg.get(MsgEnum.MSG_IMP_CSV, cont, "Nome da distribuidora"));
		Assert.notNull(retorno.getMunicipio(), Msg.get(MsgEnum.MSG_IMP_CSV, cont, "Município"));

		return retorno;
	}

}