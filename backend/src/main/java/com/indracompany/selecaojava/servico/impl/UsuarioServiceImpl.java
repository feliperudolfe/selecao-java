package com.indracompany.selecaojava.servico.impl;

import java.util.StringTokenizer;

import javax.persistence.PersistenceException;
import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indracompany.comuns.modelo.dto.Mensagem;
import com.indracompany.comuns.tratamento.NegocioException;
import com.indracompany.comuns.util.ObjectUtil;
import com.indracompany.selecaojava.app.mensagem.Msg;
import com.indracompany.selecaojava.app.mensagem.MsgEnum;
import com.indracompany.selecaojava.persistencia.modelo.entidade.Usuario;
import com.indracompany.selecaojava.persistencia.repositorio.UsuarioRepository;
import com.indracompany.selecaojava.servico.UsuarioService;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   19 de abr de 2020
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

	public static final String AUTHORRIZATION_HEADER_KEY = "Authorization";
	public static final String AUTHORRIZATION_HEADER_PREFIX = "Basic ";

	private static final Logger LOG = LoggerFactory.getLogger(UsuarioServiceImpl.class);

	private final UsuarioRepository repository;

	@Autowired
	public UsuarioServiceImpl(UsuarioRepository repository) {
		this.repository = repository;
	}

	@Override
	public String efetuarLogin(String email, String senha) {

		String retorno = null;
		try {

			String token = null;
			Usuario user = this.repository.findByEmail(email);
			if (ObjectUtil.isNull(user)) {
				throw new NegocioException(Mensagem.ALERTA, Msg.get(MsgEnum.MSG004));
			}

			if (!user.getSenha().equals(senha)) {
				throw new NegocioException(Mensagem.ALERTA, Msg.get(MsgEnum.MSG005));
			}

			token = DatatypeConverter.printBase64Binary((user.getEmail() + ":" + user.getSenha()).getBytes());
			token = AUTHORRIZATION_HEADER_PREFIX + token;
			token = AUTHORRIZATION_HEADER_KEY + "=" + token;

			retorno = token;

		} catch (PersistenceException e) {
			LOG.error(e.getMessage(), e);
			throw new NegocioException(Msg.get(MsgEnum.MSG_ERRO_PADRAO));
		}

		return retorno;
	}

	@Override
	public void cadastrar(Usuario usuario) {

		try {

			if (!usuario.getSenha().equals(usuario.getConfirmarSenha())) {
				throw new NegocioException(Mensagem.ALERTA, Msg.get(MsgEnum.MSG005));
			}

			Usuario oldUser = this.repository.findByEmail(usuario.getEmail());
			if (ObjectUtil.isNotNull(oldUser)) {
				throw new NegocioException(Mensagem.ALERTA, Msg.get(MsgEnum.MSG007));
			}

			this.repository.save(usuario);

		} catch (PersistenceException e) {
			LOG.error(e.getMessage(), e);
			throw new NegocioException(Msg.get(MsgEnum.MSG_ERRO_PADRAO));
		}
	}

	@Override
	public Usuario buscarPorToken(String token) {

		Usuario retorno = null;

		if (ObjectUtil.isNullOrEmpty(token)) {
			throw new NegocioException(Mensagem.ALERTA, Msg.get(MsgEnum.MSG006));
		}

		token = token.replace(AUTHORRIZATION_HEADER_PREFIX, "");

		final StringTokenizer cookie = new StringTokenizer(token, ";");
		while (cookie.hasMoreTokens()) {

			byte[] decoded = DatatypeConverter.parseBase64Binary(cookie.nextToken());
			String usernameAndPassword = new String(decoded);

			final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
			final String email = tokenizer.nextToken();
			final String senha = tokenizer.nextToken();

			retorno = this.buscarPorEmailESenha(email, senha);
		}

		return retorno;
	}

	@Override
	public Usuario buscarPorEmailESenha(String email, String senha) {

		Usuario retorno = null;
		try {

			retorno = this.repository.findByEmailAndSenha(email, senha);
			if (ObjectUtil.isNull(retorno)) {
				throw new NegocioException(Mensagem.ALERTA, Msg.get(MsgEnum.MSG004));
			}

		} catch (PersistenceException e) {
			LOG.error(e.getMessage(), e);
			throw new NegocioException(Msg.get(MsgEnum.MSG_ERRO_PADRAO));
		}

		return retorno;
	}

	@Override
	public void editar(String token, Usuario usuario) {

		try {

			Usuario userAtual = this.buscarPorToken(token);
			if (!usuario.getSenha().equals(usuario.getConfirmarSenha())) {
				throw new NegocioException(Mensagem.ALERTA, Msg.get(MsgEnum.MSG005));
			}

			if (!userAtual.getSenha().equals(usuario.getSenhaAtual())) {
				throw new NegocioException(Mensagem.ALERTA, Msg.get(MsgEnum.MSG008));
			}

			userAtual.setSenha(usuario.getSenha());

			this.repository.save(userAtual);

		} catch (PersistenceException e) {
			LOG.error(e.getMessage(), e);
			throw new NegocioException(Msg.get(MsgEnum.MSG_ERRO_PADRAO));
		}
	}

}