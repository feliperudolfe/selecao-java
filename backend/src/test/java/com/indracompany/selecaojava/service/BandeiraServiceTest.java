package com.indracompany.selecaojava.service;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

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
import com.indracompany.selecaojava.persistencia.modelo.entidade.Bandeira;
import com.indracompany.selecaojava.persistencia.repositorio.BandeiraRepository;
import com.indracompany.selecaojava.servico.BandeiraService;
import com.indracompany.selecaojava.servico.impl.BandeiraServiceImpl;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class BandeiraServiceTest {

	@Mock
	private BandeiraRepository repository;

    private BandeiraService service;

	@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
		service = new BandeiraServiceImpl(repository);
    }

	@Test
	@SuppressWarnings("deprecation")
	public void salvarComSucesso() {

		Bandeira entity = new Bandeira();
		entity.setCodigo(1L);
		entity.setNome("Bandeira 01");

		when(repository.save(any(Bandeira.class))).thenReturn(entity);

		Bandeira retorno = service.salvar(entity);

		assertEquals(new Long(1), retorno.getCodigo());
		assertEquals("Bandeira 01", retorno.getNome());

		verify(repository).save(any(Bandeira.class));
	}

	@Test
	@SuppressWarnings("deprecation")
	public void salvarExistente() {

		Bandeira entity = new Bandeira();
		entity.setCodigo(1L);
		entity.setNome("Bandeira 01");

		when(repository.findByNome(anyString())).thenReturn(entity);

		Bandeira retorno = service.salvar(entity);

		assertEquals(new Long(1), retorno.getCodigo());
		assertEquals("Bandeira 01", retorno.getNome());

		verify(repository).findByNome(anyString());
	}

	@Test(expected = NegocioException.class)
	public void salvarComProblemaRepository() {

		Bandeira entity = new Bandeira();
		entity.setCodigo(1L);
		entity.setNome("Bandeira 01");

		doThrow(PersistenceException.class).when(repository).save(any(Bandeira.class));

		service.salvar(entity);
	}

}