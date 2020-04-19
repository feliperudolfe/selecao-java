package com.indracompany.selecaojava.servico.impl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.WebApplicationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.indracompany.comuns.modelo.dto.Mensagem;
import com.indracompany.comuns.tratamento.NegocioException;
import com.indracompany.comuns.util.CsvUtil;
import com.indracompany.comuns.util.ObjectUtil;
import com.indracompany.selecaojava.app.config.FileStorageProperties;
import com.indracompany.selecaojava.app.mensagem.Msg;
import com.indracompany.selecaojava.app.mensagem.MsgEnum;
import com.indracompany.selecaojava.persistencia.modelo.entidade.Venda;
import com.indracompany.selecaojava.persistencia.modelo.mapper.VendaMapper;
import com.indracompany.selecaojava.servico.ImportacaoService;
import com.indracompany.selecaojava.servico.VendaService;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   18 de abr de 2020
 */
@Service
public class ImportacaoServiceImpl implements ImportacaoService {

	private static final Logger LOG = LoggerFactory.getLogger(ImportacaoServiceImpl.class);

	private final Path fileStorageLocation;

	@Autowired
	private VendaService vendaService;

	@Autowired
    public ImportacaoServiceImpl(FileStorageProperties fileStorageProperties) {

		this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();

        try {

            Files.createDirectories(this.fileStorageLocation);

        } catch (Exception ex) {
        	LOG.error(ex.getMessage(), ex);
            throw new WebApplicationException("Não foi possível criar o diretório em que os arquivos enviados serão armazenados.");
        }
    }

	@Override
	public void carregarArquivoCSV(MultipartFile file) {

		int cont = 1;
		List<String> criticas = new ArrayList<>();
		List<Venda> vendas = new ArrayList<>();
		List<List<String>> informacoes = lerInformacoesCSV(file);
		for (List<String> linha : informacoes) {

			try {

				Venda venda = VendaMapper.toEntidade(cont++, linha);
				vendas.add(venda);

			} catch (IllegalArgumentException e) {
				criticas.add(e.getMessage());
			}
		}

		if (!criticas.isEmpty()) {
			throw new NegocioException(criticas);
		}

		this.vendaService.salvar(vendas);
	}

	private synchronized List<List<String>> lerInformacoesCSV(MultipartFile file) {

		List<List<String>> retorno = null;
		try {

			String fileName = StringUtils.cleanPath(file.getOriginalFilename());

			if (ObjectUtil.isNullOrEmpty(fileName) || !fileName.endsWith(".csv")) {
				throw new NegocioException(Mensagem.ALERTA, Msg.get(MsgEnum.MSG001));
			}

			Path path = this.fileStorageLocation.resolve(fileName);
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			retorno = CsvUtil.ler(file);

		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new NegocioException(Msg.get(MsgEnum.MSG002));
		}

		return retorno;
	}

}