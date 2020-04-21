package com.indracompany.selecaojava.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import com.indracompany.selecaojava.persistencia.modelo.entidade.Municipio;
import com.indracompany.selecaojava.persistencia.repositorio.MunicipioRepository;
import com.indracompany.selecaojava.servico.EstadoService;
import com.indracompany.selecaojava.servico.MunicipioService;
import com.indracompany.selecaojava.servico.impl.MunicipioServiceImpl;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class MunicipioServiceTest {

	@Mock
	private MunicipioRepository repository;

	@Mock
	private EstadoService estadoService;

    private MunicipioService service;

	@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
		service = new MunicipioServiceImpl(repository, estadoService);
    }

	@Test
	public void salvarComSucesso() {

		Municipio entity = new Municipio();
		entity.setCodigo(1L);
		entity.setNome("João Pessoa");

		when(repository.save(any(Municipio.class))).thenReturn(entity);

		Municipio retorno = service.salvar(entity);

		assertEquals("João Pessoa", retorno.getNome());

		verify(repository).save(any(Municipio.class));
	}

	@Test
	public void salvarExistente() {

		Municipio entity = new Municipio();
		entity.setCodigo(1L);
		entity.setNome("João Pessoa");

		when(repository.findByNome(anyString())).thenReturn(entity);

		Municipio retorno = service.salvar(entity);

		assertEquals("João Pessoa", retorno.getNome());

		verify(repository).findByNome(anyString());
	}

	@Test(expected = NegocioException.class)
	public void salvarComProblemaRepository() {

		Municipio entity = new Municipio();
		entity.setCodigo(1L);
		entity.setNome("João Pessoa");

		doThrow(PersistenceException.class).when(repository).save(any(Municipio.class));

		service.salvar(entity);
	}

}