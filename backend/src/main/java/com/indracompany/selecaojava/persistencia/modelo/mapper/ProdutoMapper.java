package com.indracompany.selecaojava.persistencia.modelo.mapper;

import java.util.List;

import org.springframework.util.Assert;

import com.indracompany.selecaojava.app.mensagem.Msg;
import com.indracompany.selecaojava.app.mensagem.MsgEnum;
import com.indracompany.selecaojava.persistencia.modelo.entidade.Produto;
import com.indracompany.selecaojava.persistencia.modelo.tipo.UnidadeMedidaEnum;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   18 de abr de 2020
 */
public class ProdutoMapper {

	private static final int NOME_PRODUTO_LINHA = 5;
	private static final int UNIDADE_MEDIDA_LINHA = 9;

	private ProdutoMapper(){}

	public static Produto toEntidade(int cont, List<String> linha) {

		Produto retorno = new Produto();
		retorno.setNome(linha.get(NOME_PRODUTO_LINHA));
		retorno.setUnidadeMedida(UnidadeMedidaEnum.getPorNome(linha.get(UNIDADE_MEDIDA_LINHA)));
		retorno.setBandeira(BandeiraMapper.toEntidade(cont, linha));

		Assert.notNull(retorno.getNome(), Msg.get(MsgEnum.MSG_IMP_CSV, cont, "Nome do produto"));
		Assert.notNull(retorno.getUnidadeMedida(), Msg.get(MsgEnum.MSG_IMP_CSV, cont, "Unidade de medida"));
		Assert.notNull(retorno.getBandeira(), Msg.get(MsgEnum.MSG_IMP_CSV, cont, "Bandeira"));

		return retorno;
	}

}