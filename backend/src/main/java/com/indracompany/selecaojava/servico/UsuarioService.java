package com.indracompany.selecaojava.servico;

import com.indracompany.selecaojava.persistencia.modelo.entidade.Usuario;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   18 de abr de 2020
 */
public interface UsuarioService {

	/**
	 * Efetuar login do usuário
	 *
	 * @author 	Felipe Rudolfe Carvalho Pinheiro
	 * @since   19 de abr de 2020
	 * @param 	email
	 * @param 	senha
	 */
	String efetuarLogin(String email, String senha);

	/**
	 * Cadastra usuário na aplicação
	 *
	 * @author 	Felipe Rudolfe Carvalho Pinheiro
	 * @since   19 de abr de 2020
	 * @param 	usuario
	 */
	void cadastrar(Usuario usuario);

	/**
	 * Obter usuário através do token de autenticação
	 *
	 * @author 	Felipe Rudolfe Carvalho Pinheiro
	 * @since   19 de abr de 2020
	 * @param 	token
	 */
	Usuario buscarPorToken(String token);

	/**
	 * Obter usuário por email e senha
	 *
	 * @author 	Felipe Rudolfe Carvalho Pinheiro
	 * @since   19 de abr de 2020
	 * @param 	email
	 * @param 	senha
	 */
	Usuario buscarPorEmailESenha(String email, String senha);

	/**
	 * Editar usuário na aplicação
	 *
	 * @author 	Felipe Rudolfe Carvalho Pinheiro
	 * @since   20 de abr de 2020
	 * @param 	usuario
	 */
	void editar(String token, Usuario usuario);

}