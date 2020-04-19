package com.indracompany.selecaojava.persistencia.modelo.mapper;

import java.util.List;

import org.springframework.util.Assert;

import com.indracompany.comuns.util.DataUtil;
import com.indracompany.comuns.util.MonetarioUtil;
import com.indracompany.selecaojava.app.mensagem.Msg;
import com.indracompany.selecaojava.app.mensagem.MsgEnum;
import com.indracompany.selecaojava.persistencia.modelo.entidade.Venda;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   18 de abr de 2020
 */
public class VendaMapper {

	private static final int DATA_COLETA_LINHA = 6;
	private static final int VALOR_VENDA_LINHA = 7;
	private static final int VALOR_COMPRA_LINHA = 8;
	private static final String FORMATO_DATA = "dd/MM/yyyy";

	private VendaMapper(){}

	public static Venda toEntidade(int cont, List<String> linha) {

		Venda retorno = new Venda();
		retorno.setDataColeta(DataUtil.toDate(linha.get(DATA_COLETA_LINHA), FORMATO_DATA));
		retorno.setValorVenda(MonetarioUtil.toDouble(linha.get(VALOR_VENDA_LINHA)));
		retorno.setValorCompra(MonetarioUtil.toDouble(linha.get(VALOR_COMPRA_LINHA)));
		retorno.setDistribuidora(DistribuidoraMapper.toEntidade(cont, linha));
		retorno.setProduto(ProdutoMapper.toEntidade(cont, linha));

		Assert.notNull(retorno.getDataColeta(), Msg.getMessage(MsgEnum.MSG_IMP_CSV, cont, "Data da coleta"));
		Assert.notNull(retorno.getValorVenda(), Msg.getMessage(MsgEnum.MSG_IMP_CSV, cont, "Valor da venda"));
		Assert.notNull(retorno.getDistribuidora(), Msg.getMessage(MsgEnum.MSG_IMP_CSV,cont, "Distribuidora"));
		Assert.notNull(retorno.getProduto(), Msg.getMessage(MsgEnum.MSG_IMP_CSV, cont, "Produto"));

		return retorno;
	}

}