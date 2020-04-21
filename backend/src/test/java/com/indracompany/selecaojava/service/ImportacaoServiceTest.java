package com.indracompany.selecaojava.service;

import static org.junit.Assert.assertTrue;

import javax.ws.rs.WebApplicationException;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.indracompany.comuns.modelo.Base64CsvMultipartFile;
import com.indracompany.comuns.tratamento.NegocioException;
import com.indracompany.selecaojava.app.config.FileStorageProperties;
import com.indracompany.selecaojava.servico.ImportacaoService;
import com.indracompany.selecaojava.servico.VendaService;
import com.indracompany.selecaojava.servico.impl.ImportacaoServiceImpl;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class ImportacaoServiceTest {

	@Mock
	private VendaService vendaService;

    private ImportacaoService service;

	@Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        FileStorageProperties fileStorageProperties = new FileStorageProperties();
        fileStorageProperties.setUploadDir("/temp/file/uploads");

		service = new ImportacaoServiceImpl(vendaService, fileStorageProperties);
    }

	@Test
	public void salvarComSucesso() {

		String base64 = "data:application/vnd.ms-excel;base64,//5SAGUAZwBpAOMAbwAgAC0AIABTAGkAZwBsAGEACQBFAHMAdABhAGQAbwAgAC0AIABTAGkAZwBsAGEACQBNAHUAbgBpAGMA7QBwAGkAbwAJAFIAZQB2AGUAbgBkAGEACQBDAE4AUABKACAAZABhACAAUgBlAHYAZQBuAGQAYQAJAFAAcgBvAGQAdQB0AG8ACQBEAGEAdABhACAAZABhACAAQwBvAGwAZQB0AGEACQBWAGEAbABvAHIAIABkAGUAIABWAGUAbgBkAGEACQBWAGEAbABvAHIAIABkAGUAIABDAG8AbQBwAHIAYQAJAFUAbgBpAGQAYQBkAGUAIABkAGUAIABNAGUAZABpAGQAYQAJAEIAYQBuAGQAZQBpAHIAYQANAAoAQwBPAAkARABGAAkAQgBSAEEAUwBJAEwASQBBAAkAQQBCAFIASQBUAFQAQQAgAFAATwBTAFQATwBTACAARABFACAAUwBFAFIAVgBJAMcATwBTACAATABUAEQAQQAJADEAMAA2ADYAOAA4ADYAMwAwADAAMAAxADkANQAJAEQASQBFAFMARQBMAAkAMQA2AC8AMAAxAC8AMgAwADEAOQAJADMALAA3ADkAOQAJADMALAAyADEANgA2AAkAUgAkACAALwAgAGwAaQB0AHIAbwAJAFIAQQBJAFoARQBOAA0ACgA=";
		Base64CsvMultipartFile file = new Base64CsvMultipartFile(base64);

		service.carregarArquivoCSV(file);

		assertTrue(Boolean.TRUE);
	}

	@Test(expected = NegocioException.class)
	public void salvarComBase64Invalida() {

		String base64 = "//5SAGUAZwBpAOMAbwAgAC0AIABTAGkAZwBsAGEACQBFAHMAdABhAGQAbwAgAC0AIABTAGkAZwBsAGEACQBNAHUAbgBpAGMA7QBwAGkAbwAJAFIAZQB2AGUAbgBkAGEACQBDAE4AUABKACAAZABhACAAUgBlAHYAZQBuAGQAYQAJAFAAcgBvAGQAdQB0AG8ACQBEAGEAdABhACAAZABhACAAQwBvAGwAZQB0AGEACQBWAGEAbABvAHIAIABkAGUAIABWAGUAbgBkAGEACQBWAGEAbABvAHIAIABkAGUAIABDAG8AbQBwAHIAYQAJAFUAbgBpAGQAYQBkAGUAIABkAGUAIABNAGUAZABpAGQAYQAJAEIAYQBuAGQAZQBpAHIAYQANAAoAQwBPAAkARABGAAkAQgBSAEEAUwBJAEwASQBBAAkAQQBCAFIASQBUAFQAQQAgAFAATwBTAFQATwBTACAARABFACAAUwBFAFIAVgBJAMcATwBTACAATABUAEQAQQAJADEAMAA2ADYAOAA4ADYAMwAwADAAMAAxADkANQAJAEQASQBFAFMARQBMAAkAMQA2AC8AMAAxAC8AMgAwADEAOQAJADMALAA3ADkAOQAJADMALAAyADEANgA2AAkAUgAkACAALwAgAGwAaQB0AHIAbwAJAFIAQQBJAFoARQBOAA0ACgA=";
		Base64CsvMultipartFile file = new Base64CsvMultipartFile(base64);

		service.carregarArquivoCSV(file);
	}

	@Test(expected = NegocioException.class)
	public void salvarComCriticasArquivo() {

		String base64 = "data:application/vnd.ms-excel;base64,//5SAGUAZwBpAOMAbwAgAC0AIABTAGkAZwBsAGEACQBFAHMAdABhAGQAbwAgAC0AIABTAGkAZwBsAGEACQBNAHUAbgBpAGMA7QBwAGkAbwAJAFIAZQB2AGUAbgBkAGEACQBDAE4AUABKACAAZABhACAAUgBlAHYAZQBuAGQAYQAJAFAAcgBvAGQAdQB0AG8ACQBEAGEAdABhACAAZABhACAAQwBvAGwAZQB0AGEACQBWAGEAbABvAHIAIABkAGUAIABWAGUAbgBkAGEACQBWAGEAbABvAHIAIABkAGUAIABDAG8AbQBwAHIAYQAJAFUAbgBpAGQAYQBkAGUAIABkAGUAIABNAGUAZABpAGQAYQAJAEIAYQBuAGQAZQBpAHIAYQANAAoAQwBPAAkARABGAAkAQgBSAEEAUwBJAEwASQBBAAkAQQBCAFIASQBUAFQAQQAgAFAATwBTAFQATwBTACAARABFACAAUwBFAFIAVgBJAMcATwBTACAATABUAEQAQQAJAAkARABJAEUAUwBFAEwACQAxADYALwAwADEALwAyADAAMQA5AAkAMwAsADcAOQA5AAkAMwAsADIAMQA2ADYACQBSACQAIAAvACAAbABpAHQAcgBvAAkAUgBBAEkAWgBFAE4ADQAKAA==";
		Base64CsvMultipartFile file = new Base64CsvMultipartFile(base64);

		service.carregarArquivoCSV(file);
	}

	@Test(expected = WebApplicationException.class)
	public void salvarComProblemaCriacaoPath() {

		FileStorageProperties fileStorageProperties = new FileStorageProperties();

		service = new ImportacaoServiceImpl(vendaService, fileStorageProperties);
	}

}