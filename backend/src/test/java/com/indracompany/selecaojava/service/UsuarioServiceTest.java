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
import com.indracompany.selecaojava.persistencia.modelo.entidade.Usuario;
import com.indracompany.selecaojava.persistencia.repositorio.UsuarioRepository;
import com.indracompany.selecaojava.servico.UsuarioService;
import com.indracompany.selecaojava.servico.impl.UsuarioServiceImpl;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class UsuarioServiceTest {

	@Mock
	private UsuarioRepository repository;

    private UsuarioService service;

	@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
		service = new UsuarioServiceImpl(repository);
    }

	@Test
	public void efetuarLoginComSucesso() {

		String email = "teste@teste.com";
		String senha = "123456";
		String token = "Authorization=Basic dGVzdGVAdGVzdGUuY29tOjEyMzQ1Ng==";

		Usuario entity = new Usuario();
		entity.setCodigo(1L);
		entity.setEmail("teste@teste.com");
		entity.setSenha("123456");
		entity.setConfirmarSenha("123456");

		when(repository.findByEmail(anyString())).thenReturn(entity);

		String retorno = service.efetuarLogin(email, senha);

		assertEquals(token, retorno);;

		verify(repository).findByEmail(anyString());
	}

	@Test(expected = NegocioException.class)
	public void efetuarLoginComUsuarioInexistente() {

		String email = "teste@teste.com";
		String senha = "123456";

		service.efetuarLogin(email, senha);
	}

	@Test(expected = NegocioException.class)
	public void efetuarLoginComSenhaDiferente() {

		String email = "teste@teste.com";
		String senha = "123456";

		Usuario entity = new Usuario();
		entity.setCodigo(1L);
		entity.setEmail("teste@teste.com");
		entity.setSenha("654321");

		when(repository.findByEmail(anyString())).thenReturn(entity);

		service.efetuarLogin(email, senha);
	}

	@Test(expected = NegocioException.class)
	public void efetuarLoginComProblemaRepository() {

		String email = "teste@teste.com";
		String senha = "123456";

		doThrow(PersistenceException.class).when(repository).findByEmail(anyString());

		service.efetuarLogin(email, senha);
	}

	@Test
	public void cadastrarComSucesso() {

		Usuario entity = new Usuario();
		entity.setCodigo(1L);
		entity.setEmail("teste@teste.com");
		entity.setSenha("123456");
		entity.setConfirmarSenha("123456");

		when(repository.save(any(Usuario.class))).thenReturn(entity);

		service.cadastrar(entity);

		assertTrue(Boolean.TRUE);

		verify(repository).save(any(Usuario.class));
	}

	@Test(expected = NegocioException.class)
	public void cadastrarComConfirmacaoSenhaDiferente() {

		Usuario entity = new Usuario();
		entity.setCodigo(1L);
		entity.setEmail("teste@teste.com");
		entity.setSenha("123456");
		entity.setConfirmarSenha("654321");

		service.cadastrar(entity);
	}

	@Test(expected = NegocioException.class)
	public void cadastrarComEmailJaCadastrado() {

		Usuario entity = new Usuario();
		entity.setCodigo(1L);
		entity.setEmail("teste@teste.com");
		entity.setSenha("123456");
		entity.setConfirmarSenha("123456");

		when(repository.findByEmail(anyString())).thenReturn(entity);

		service.cadastrar(entity);
	}

	@Test(expected = NegocioException.class)
	public void cadastrarComProblemaRepository() {

		Usuario entity = new Usuario();
		entity.setCodigo(1L);
		entity.setEmail("teste@teste.com");
		entity.setSenha("123456");
		entity.setConfirmarSenha("123456");

		doThrow(PersistenceException.class).when(repository).save(any(Usuario.class));

		service.cadastrar(entity);
	}

	@Test
	public void buscarPorTokenComSucesso() {

		String token = "Basic dGVzdGVAdGVzdGUuY29tOjEyMzQ1Ng==";

		Usuario entity = new Usuario();
		entity.setCodigo(1L);
		entity.setEmail("teste@teste.com");
		entity.setSenha("123456");
		entity.setConfirmarSenha("123456");

		when(repository.findByEmailAndSenha(anyString(), anyString())).thenReturn(entity);

		Usuario retorno = service.buscarPorToken(token);

		assertEquals("teste@teste.com", retorno.getEmail());;

		verify(repository).findByEmailAndSenha(anyString(), anyString());
	}

	@Test(expected = NegocioException.class)
	public void buscarPorTokenNull() {

		String token = null;

		service.buscarPorToken(token);
	}

	@Test(expected = NegocioException.class)
	public void buscarPorTokenVazio() {

		String token = "";

		service.buscarPorToken(token);
	}

	@Test
	public void buscarPorEmailESenhaComSucesso() {

		String email = "teste@teste.com";
		String senha = "123456";

		Usuario entity = new Usuario();
		entity.setCodigo(1L);
		entity.setEmail("teste@teste.com");
		entity.setSenha("123456");
		entity.setConfirmarSenha("123456");

		when(repository.findByEmailAndSenha(anyString(), anyString())).thenReturn(entity);

		Usuario retorno = service.buscarPorEmailESenha(email, senha);

		assertEquals("teste@teste.com", retorno.getEmail());;

		verify(repository).findByEmailAndSenha(anyString(), anyString());
	}

	@Test(expected = NegocioException.class)
	public void buscarPorEmailESenhaComUsuarioInexistente() {

		String email = "teste@teste.com";
		String senha = "123456";

		Usuario entity = null;

		when(repository.findByEmailAndSenha(anyString(), anyString())).thenReturn(entity);

		service.buscarPorEmailESenha(email, senha);
	}

	@Test(expected = NegocioException.class)
	public void buscarPorEmailESenhaComProblemaDAO() {

		String email = "teste@teste.com";
		String senha = "123456";

		doThrow(PersistenceException.class).when(repository).findByEmailAndSenha(anyString(), anyString());

		service.buscarPorEmailESenha(email, senha);
	}

	@Test
	public void editarComSucesso() {

		String token = "Basic dGVzdGVAdGVzdGUuY29tOjEyMzQ1Ng==";

		Usuario usuario = new Usuario();
		usuario.setCodigo(1L);
		usuario.setEmail("teste@teste.com");
		usuario.setSenha("123456");
		usuario.setConfirmarSenha("123456");
		usuario.setSenhaAtual("654321");

		Usuario entity = new Usuario();
		entity.setCodigo(1L);
		entity.setEmail("teste@teste.com");
		entity.setSenha("654321");

		when(repository.findByEmailAndSenha(anyString(), anyString())).thenReturn(entity);
		when(repository.save(any(Usuario.class))).thenReturn(entity);

		service.editar(token, usuario);

		assertTrue(Boolean.TRUE);

		verify(repository).save(any(Usuario.class));
	}

	@Test(expected = NegocioException.class)
	public void editarComProblemaRepositorio() {

		String token = "Basic dGVzdGVAdGVzdGUuY29tOjEyMzQ1Ng==";

		Usuario usuario = new Usuario();
		usuario.setCodigo(1L);
		usuario.setEmail("teste@teste.com");
		usuario.setSenha("123456");
		usuario.setConfirmarSenha("123456");
		usuario.setSenhaAtual("654321");

		Usuario entity = new Usuario();
		entity.setCodigo(1L);
		entity.setEmail("teste@teste.com");
		entity.setSenha("654321");

		when(repository.findByEmailAndSenha(anyString(), anyString())).thenReturn(entity);
		doThrow(PersistenceException.class).when(repository).save(any(Usuario.class));

		service.editar(token, usuario);
	}

	@Test(expected = NegocioException.class)
	public void editarComConfirmacaoSenhaDiferente() {

		String token = "Basic dGVzdGVAdGVzdGUuY29tOjEyMzQ1Ng==";

		Usuario usuario = new Usuario();
		usuario.setCodigo(1L);
		usuario.setEmail("teste@teste.com");
		usuario.setSenha("123456");
		usuario.setConfirmarSenha("654321");
		usuario.setSenhaAtual("654321");

		Usuario entity = new Usuario();
		entity.setCodigo(1L);
		entity.setEmail("teste@teste.com");
		entity.setSenha("654321");

		when(repository.findByEmailAndSenha(anyString(), anyString())).thenReturn(entity);

		service.editar(token, usuario);
	}

	@Test(expected = NegocioException.class)
	public void editarComSenhaAtuaçDiferente() {

		String token = "Basic dGVzdGVAdGVzdGUuY29tOjEyMzQ1Ng==";

		Usuario usuario = new Usuario();
		usuario.setCodigo(1L);
		usuario.setEmail("teste@teste.com");
		usuario.setSenha("123456");
		usuario.setConfirmarSenha("123456");
		usuario.setSenhaAtual("123456");

		Usuario entity = new Usuario();
		entity.setCodigo(1L);
		entity.setEmail("teste@teste.com");
		entity.setSenha("654321");

		when(repository.findByEmailAndSenha(anyString(), anyString())).thenReturn(entity);

		service.editar(token, usuario);
	}

}