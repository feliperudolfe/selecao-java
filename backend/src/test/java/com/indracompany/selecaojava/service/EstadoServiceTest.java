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
import com.indracompany.selecaojava.persistencia.modelo.entidade.Estado;
import com.indracompany.selecaojava.persistencia.modelo.tipo.RegiaoEnum;
import com.indracompany.selecaojava.persistencia.repositorio.EstadoRepository;
import com.indracompany.selecaojava.servico.EstadoService;
import com.indracompany.selecaojava.servico.impl.EstadoServiceImpl;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class EstadoServiceTest {

	@Mock
	private EstadoRepository repository;

    private EstadoService service;

	@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
		service = new EstadoServiceImpl(repository);
    }

	@Test
	public void salvarComSucesso() {

		Estado entity = new Estado();
		entity.setCodigo("PB");
		entity.setRegiao(RegiaoEnum.NE);

		when(repository.save(any(Estado.class))).thenReturn(entity);

		Estado retorno = service.salvar(entity);

		assertEquals("PB", retorno.getCodigo());
		assertEquals(RegiaoEnum.NE, retorno.getRegiao());

		verify(repository).save(any(Estado.class));
	}

	@Test
	public void salvarExistente() {

		Estado entity = new Estado();
		entity.setCodigo("PB");
		entity.setRegiao(RegiaoEnum.NE);

		when(repository.findByCodigo(anyString())).thenReturn(entity);

		Estado retorno = service.salvar(entity);

		assertEquals("PB", retorno.getCodigo());
		assertEquals(RegiaoEnum.NE, retorno.getRegiao());

		verify(repository).findByCodigo(anyString());
	}

	@Test(expected = NegocioException.class)
	public void salvarComProblemaRepository() {

		Estado entity = new Estado();
		entity.setCodigo("PB");
		entity.setRegiao(RegiaoEnum.NE);

		doThrow(PersistenceException.class).when(repository).save(any(Estado.class));

		service.salvar(entity);
	}

}