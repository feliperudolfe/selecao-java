package com.indracompany.selecaojava.service;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

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
import com.indracompany.selecaojava.persistencia.modelo.entidade.Distribuidora;
import com.indracompany.selecaojava.persistencia.repositorio.DistribuidoraRepository;
import com.indracompany.selecaojava.servico.DistribuidoraService;
import com.indracompany.selecaojava.servico.MunicipioService;
import com.indracompany.selecaojava.servico.impl.DistribuidoraServiceImpl;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class DistribuidoraServiceTest {

	@Mock
	private DistribuidoraRepository repository;

	@Mock
	private MunicipioService municipioService;

    private DistribuidoraService service;

	@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
		service = new DistribuidoraServiceImpl(repository, municipioService);
    }

	@Test
	@SuppressWarnings("deprecation")
	public void salvarComSucesso() {

		Distribuidora entity = new Distribuidora();
		entity.setCodigo(1L);
		entity.setCnpj("123456789101112");
		entity.setNome("Distribuidora 01");

		when(repository.save(any(Distribuidora.class))).thenReturn(entity);

		Distribuidora retorno = service.salvar(entity);

		assertEquals(new Long(1), retorno.getCodigo());
		assertEquals("Distribuidora 01", retorno.getNome());

		verify(repository).save(any(Distribuidora.class));
	}

	@Test
	@SuppressWarnings("deprecation")
	public void salvarExistente() {

		Distribuidora entity = new Distribuidora();
		entity.setCodigo(1L);
		entity.setCnpj("123456789101112");
		entity.setNome("Distribuidora 01");

		when(repository.findByCnpj(anyString())).thenReturn(entity);

		Distribuidora retorno = service.salvar(entity);

		assertEquals(new Long(1), retorno.getCodigo());
		assertEquals("Distribuidora 01", retorno.getNome());

		verify(repository).findByCnpj(anyString());
	}

	@Test(expected = NegocioException.class)
	public void salvarComProblemaRepository() {

		Distribuidora entity = new Distribuidora();
		entity.setCodigo(1L);
		entity.setCnpj("123456789101112");
		entity.setNome("Distribuidora 01");

		doThrow(PersistenceException.class).when(repository).save(any(Distribuidora.class));

		service.salvar(entity);
	}

	@Test
	public void listarOptionsComSucesso() {

		List<OptionDTO> options = new ArrayList<>();
		options.add(new OptionDTO(1L, "Distribuidora 01"));

		when(repository.listOptions()).thenReturn(options);

		List<OptionDTO> retorno = service.listarOptions();

		assertEquals(1, retorno.size());
		assertEquals("Distribuidora 01", retorno.get(0).getValor());

		verify(repository).listOptions();
	}

	@Test(expected = NegocioException.class)
	public void listarOptionsComProblemaRepository() {

		doThrow(PersistenceException.class).when(repository).listOptions();

		service.listarOptions();
	}

	@Test
	public void consultarPorCodigoComSucesso() {

		Distribuidora entity = new Distribuidora();
		entity.setCodigo(1L);
		entity.setCnpj("123456789101112");
		entity.setNome("Distribuidora 01");

		when(repository.findByCodigo(anyLong())).thenReturn(entity);

		Distribuidora retorno = service.consultarPorCodigo(1L);

		assertEquals("Distribuidora 01", retorno.getNome());

		verify(repository).findByCodigo(anyLong());
	}

	@Test(expected = NegocioException.class)
	public void consultarPorCodigoInexistente() {

		Distribuidora entity = null;

		when(repository.findByCodigo(anyLong())).thenReturn(entity);

		service.consultarPorCodigo(1L);
	}

	@Test(expected = NegocioException.class)
	public void consultarPorCodigoComProblemaRepository() {

		doThrow(PersistenceException.class).when(repository).findByCodigo(anyLong());

		service.consultarPorCodigo(1L);
	}

}