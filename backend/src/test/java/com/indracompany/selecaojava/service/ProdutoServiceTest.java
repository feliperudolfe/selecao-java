package com.indracompany.selecaojava.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.indracompany.comuns.tratamento.NegocioException;
import com.indracompany.selecaojava.persistencia.modelo.dto.OptionDTO;
import com.indracompany.selecaojava.persistencia.modelo.entidade.Bandeira;
import com.indracompany.selecaojava.persistencia.modelo.entidade.Produto;
import com.indracompany.selecaojava.persistencia.repositorio.ProdutoRepository;
import com.indracompany.selecaojava.servico.BandeiraService;
import com.indracompany.selecaojava.servico.ProdutoService;
import com.indracompany.selecaojava.servico.impl.ProdutoServiceImpl;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class ProdutoServiceTest {

	@Mock
	private ProdutoRepository repository;

	@Mock
	private BandeiraService bandeiraService;;

    private ProdutoService service;

	@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
		service = new ProdutoServiceImpl(repository, bandeiraService);
    }

	@Test
	public void salvarComSucesso() {

		Produto entity = new Produto();
		entity.setCodigo(1L);
		entity.setNome("Produto 01");

		when(repository.save(any(Produto.class))).thenReturn(entity);

		Produto retorno = service.salvar(entity);

		assertEquals("Produto 01", retorno.getNome());

		verify(repository).save(any(Produto.class));
	}

	@Test
	public void salvarExistente() {

		Bandeira bandeira = new Bandeira();
		bandeira.setCodigo(1L);
		bandeira.setNome("Bandeira 01");

		Produto entity = new Produto();
		entity.setCodigo(1L);
		entity.setNome("Produto 01");

		when(repository.findByNomeAndBandeira(anyString(), any())).thenReturn(entity);

		Produto retorno = service.salvar(entity);

		assertEquals("Produto 01", retorno.getNome());

		verify(repository).findByNomeAndBandeira(anyString(), any());
	}

	@Test(expected = NegocioException.class)
	public void salvarComProblemaRepository() {

		Produto entity = new Produto();
		entity.setCodigo(1L);
		entity.setNome("Produto 01");

		doThrow(PersistenceException.class).when(repository).save(any(Produto.class));

		service.salvar(entity);
	}

	@Test
	public void listarOptionsComSucesso() {

		List<OptionDTO> options = new ArrayList<>();
		options.add(new OptionDTO(1L, "Produto 01"));

		when(repository.listOptions()).thenReturn(options);

		List<OptionDTO> retorno = service.listarOptions();

		assertEquals(1, retorno.size());
		assertEquals("Produto 01", retorno.get(0).getValor());

		verify(repository).listOptions();
	}

	@Test(expected = NegocioException.class)
	public void listarOptionsComProblemaRepository() {

		doThrow(PersistenceException.class).when(repository).listOptions();

		service.listarOptions();
	}

	@Test
	public void consultarPorCodigoComSucesso() {

		Produto entity = new Produto();
		entity.setCodigo(1L);
		entity.setNome("Produto 01");

		when(repository.findByCodigo(anyLong())).thenReturn(entity);

		Produto retorno = service.consultarPorCodigo(1L);

		assertEquals("Produto 01", retorno.getNome());

		verify(repository).findByCodigo(anyLong());
	}

	@Test(expected = NegocioException.class)
	public void consultarPorCodigoInexistente() {

		Produto entity = null;

		when(repository.findByCodigo(anyLong())).thenReturn(entity);

		service.consultarPorCodigo(1L);
	}

	@Test(expected = NegocioException.class)
	public void consultarPorCodigoComProblemaRepository() {

		doThrow(PersistenceException.class).when(repository).findByCodigo(anyLong());

		service.consultarPorCodigo(1L);
	}

}