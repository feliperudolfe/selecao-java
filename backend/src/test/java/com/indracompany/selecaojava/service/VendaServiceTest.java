package com.indracompany.selecaojava.service;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
import com.indracompany.selecaojava.persistencia.dao.VendaDAO;
import com.indracompany.selecaojava.persistencia.modelo.dto.VendaDTO;
import com.indracompany.selecaojava.persistencia.modelo.dto.VendaPaginadorDTO;
import com.indracompany.selecaojava.persistencia.modelo.entidade.Distribuidora;
import com.indracompany.selecaojava.persistencia.modelo.entidade.Produto;
import com.indracompany.selecaojava.persistencia.modelo.entidade.Venda;
import com.indracompany.selecaojava.persistencia.repositorio.VendaRepository;
import com.indracompany.selecaojava.servico.DistribuidoraService;
import com.indracompany.selecaojava.servico.ProdutoService;
import com.indracompany.selecaojava.servico.VendaService;
import com.indracompany.selecaojava.servico.impl.VendaServiceImpl;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class VendaServiceTest {

	@Mock
	private VendaRepository repository;

	@Mock
	private VendaDAO dao;

	@Mock
	private ProdutoService produtoService;

	@Mock
	private DistribuidoraService distribuidoraService;

    private VendaService service;

	@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
		service = new VendaServiceImpl(repository, dao, produtoService, distribuidoraService);
    }

	@Test
	@SuppressWarnings("deprecation")
	public void salvarComSucesso() {

		Venda entity = new Venda();
		entity.setCodigo(1L);
		entity.setDataColeta(new Date());
		entity.setValorVenda(3.55);
		entity.setValorCompra(3.86);

		when(repository.save(any(Venda.class))).thenReturn(entity);

		Venda retorno = service.salvar(entity);

		assertEquals(new Double(3.55), retorno.getValorVenda());
		assertEquals(new Double(3.86), retorno.getValorCompra());

		verify(repository).save(any(Venda.class));
	}

	@Test(expected = NegocioException.class)
	public void salvarComProblemaRepository() {

		Venda entity = new Venda();
		entity.setCodigo(1L);
		entity.setDataColeta(new Date());
		entity.setValorVenda(3.55);
		entity.setValorCompra(3.86);

		doThrow(PersistenceException.class).when(repository).save(any(Venda.class));

		service.salvar(entity);
	}

	@Test
	public void salvarListaComSucesso() {

		Venda entity = new Venda();
		entity.setCodigo(1L);
		entity.setDataColeta(new Date());
		entity.setValorVenda(3.55);
		entity.setValorCompra(3.86);

		List<Venda> vendas = new ArrayList<>();
		vendas.add(entity);

		when(repository.save(any(Venda.class))).thenReturn(entity);

		service.salvar(vendas);

		assertTrue(Boolean.TRUE);

		verify(repository).save(any(Venda.class));
	}

	@Test(expected = NegocioException.class)
	public void salvarListaComProblemaDAO() {

		Venda entity = new Venda();
		entity.setCodigo(1L);
		entity.setDataColeta(new Date());
		entity.setValorVenda(3.55);
		entity.setValorCompra(3.86);

		List<Venda> vendas = new ArrayList<>();
		vendas.add(entity);

		doThrow(PersistenceException.class).when(repository).save(any(Venda.class));

		service.salvar(vendas);
	}

	@Test
	public void listarComSucesso() {

		Venda entity = new Venda();
		entity.setCodigo(1L);
		entity.setDataColeta(new Date());
		entity.setValorVenda(3.55);
		entity.setValorCompra(3.86);

		VendaDTO dto = entity.toDTO(VendaDTO.class);

		List<VendaDTO> list = new ArrayList<>();
		list.add(dto);

		VendaPaginadorDTO paginator = new VendaPaginadorDTO(0, 1, 100L, list);

		when(dao.listar(any(VendaPaginadorDTO.class))).thenReturn(paginator);

		VendaPaginadorDTO retorno = service.listar(paginator);

		assertEquals(1, retorno.getList().size());

		verify(dao).listar(any(VendaPaginadorDTO.class));
	}

	@Test(expected = NegocioException.class)
	public void listarComProblemaDAO() {

		Venda entity = new Venda();
		entity.setCodigo(1L);
		entity.setDataColeta(new Date());
		entity.setValorVenda(3.55);
		entity.setValorCompra(3.86);

		VendaDTO dto = entity.toDTO(VendaDTO.class);

		List<VendaDTO> list = new ArrayList<>();
		list.add(dto);

		VendaPaginadorDTO paginator = new VendaPaginadorDTO(0, 1, 100L, list);

		doThrow(PersistenceException.class).when(dao).listar(any(VendaPaginadorDTO.class));

		service.listar(paginator);
	}

	@Test
	public void removerComSucesso() {

		Long codigo = 1L;

		doNothing().when(repository).deleteById(anyLong());

		service.remover(codigo);

		assertTrue(Boolean.TRUE);

		verify(repository).deleteById(anyLong());
	}

	@Test(expected = NegocioException.class)
	public void removerComProblemaRepositorio() {

		Long codigo = 1L;

		doThrow(PersistenceException.class).when(repository).deleteById(anyLong());

		service.remover(codigo);
	}

	@Test
	@SuppressWarnings("deprecation")
	public void obterPorCodigoComSucesso() {

		Long codigo = 1L;

		Venda entity = new Venda();
		entity.setCodigo(1L);
		entity.setDataColeta(new Date());
		entity.setValorVenda(3.55);
		entity.setValorCompra(3.86);

		Optional<Venda> opt = Optional.of(entity);

		when(repository.findById(anyLong())).thenReturn(opt);

		VendaDTO retorno = service.obterPorCodigo(codigo);

		assertEquals(new Double(3.55), retorno.getValorVenda());
		assertEquals(new Double(3.86), retorno.getValorCompra());

		verify(repository).findById(anyLong());
	}

	@Test(expected = NegocioException.class)
	public void obterPorCodigoInexistente() {

		Long codigo = 1L;

		Optional<Venda> opt = Optional.empty();

		when(repository.findById(anyLong())).thenReturn(opt);

		service.obterPorCodigo(codigo);
	}

	@Test(expected = NegocioException.class)
	public void obterPorCodigoRepositorio() {

		Long codigo = 1L;

		doThrow(PersistenceException.class).when(repository).findById(anyLong());

		service.obterPorCodigo(codigo);
	}

	@Test
	public void atualizarComSucesso() {

		Produto produto = new Produto();
		produto.setCodigo(1L);

		Distribuidora distribuidora = new Distribuidora();
		distribuidora.setCodigo(1L);

		Long codigo = 1L;
		Venda entity = new Venda();
		entity.setCodigo(1L);
		entity.setDataColeta(new Date());
		entity.setValorVenda(3.55);
		entity.setValorCompra(3.86);
		entity.setProduto(produto);
		entity.setDistribuidora(distribuidora);

		VendaDTO vendaDTO = entity.toDTO(VendaDTO.class);

		Optional<Venda> opt = Optional.of(entity);

		when(repository.findById(anyLong())).thenReturn(opt);
		when(repository.save(any(Venda.class))).thenReturn(entity);

		service.atualizar(codigo, vendaDTO);

		assertTrue(Boolean.TRUE);

		verify(repository).findById(anyLong());
		verify(repository).save(any(Venda.class));
	}

	@Test(expected = NegocioException.class)
	public void atualizarVendaInexistente() {

		Produto produto = new Produto();
		produto.setCodigo(1L);

		Distribuidora distribuidora = new Distribuidora();
		distribuidora.setCodigo(1L);

		Long codigo = 1L;
		Venda entity = new Venda();
		entity.setCodigo(1L);
		entity.setDataColeta(new Date());
		entity.setValorVenda(3.55);
		entity.setValorCompra(3.86);
		entity.setProduto(produto);
		entity.setDistribuidora(distribuidora);

		VendaDTO vendaDTO = entity.toDTO(VendaDTO.class);

		Optional<Venda> opt = Optional.empty();

		when(repository.findById(anyLong())).thenReturn(opt);

		service.atualizar(codigo, vendaDTO);
	}

	@Test(expected = NegocioException.class)
	public void atualizarComProblemaRepositorio() {

		Produto produto = new Produto();
		produto.setCodigo(1L);

		Distribuidora distribuidora = new Distribuidora();
		distribuidora.setCodigo(1L);

		Long codigo = 1L;
		Venda entity = new Venda();
		entity.setCodigo(1L);
		entity.setDataColeta(new Date());
		entity.setValorVenda(3.55);
		entity.setValorCompra(3.86);
		entity.setProduto(produto);
		entity.setDistribuidora(distribuidora);

		VendaDTO vendaDTO = entity.toDTO(VendaDTO.class);

		Optional<Venda> opt = Optional.of(entity);

		when(repository.findById(anyLong())).thenReturn(opt);
		doThrow(PersistenceException.class).when(repository).save(any(Venda.class));

		service.atualizar(codigo, vendaDTO);
	}

	@Test
	@SuppressWarnings("deprecation")
	public void obterMediaComSucesso() {

		Long municipio = 1L;
		String nomeMunicipio = "Nome municipio";
		Long bandeira = 1L;

		Venda entity = new Venda();
		entity.setCodigo(1L);
		entity.setDataColeta(new Date());
		entity.setValorVenda(3.55);
		entity.setValorCompra(3.86);

		VendaDTO vendaDTO = entity.toDTO(VendaDTO.class);

		when(dao.obterMedia(anyLong(), anyString(), anyLong())).thenReturn(vendaDTO);

		VendaDTO retorno = service.obterMedia(municipio, nomeMunicipio, bandeira);

		assertEquals(new Double(3.55), retorno.getValorVenda());
		assertEquals(new Double(3.86), retorno.getValorCompra());

		verify(dao).obterMedia(anyLong(), anyString(), anyLong());
	}

	@Test(expected = NegocioException.class)
	public void obterMediaComProblemaDAO() {

		Long municipio = 1L;
		String nomeMunicipio = "Nome municipio";
		Long bandeira = 1L;

		doThrow(PersistenceException.class).when(dao).obterMedia(anyLong(), anyString(), anyLong());

		service.obterMedia(municipio, nomeMunicipio, bandeira);
	}

}