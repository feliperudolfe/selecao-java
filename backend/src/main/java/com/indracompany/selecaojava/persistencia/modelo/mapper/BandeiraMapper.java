package com.indracompany.selecaojava.persistencia.modelo.mapper;

import java.util.List;

import org.springframework.util.Assert;

import com.indracompany.selecaojava.app.mensagem.Msg;
import com.indracompany.selecaojava.app.mensagem.MsgEnum;
import com.indracompany.selecaojava.persistencia.modelo.entidade.Bandeira;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   18 de abr de 2020
 */
public class BandeiraMapper {

	private static final int BANDEIRA_LINHA = 10;

	private BandeiraMapper(){}

	public static Bandeira toEntidade(int cont, List<String> linha) {

		Bandeira retorno = new Bandeira();
		retorno.setNome(linha.get(BANDEIRA_LINHA));

		Assert.hasText(retorno.getNome(), Msg.get(MsgEnum.MSG_IMP_CSV, cont, "Nome da bandeira"));

		return retorno;
	}

}