package com.indracompany.selecaojava.persistencia.modelo.mapper;

import com.indracompany.selecaojava.persistencia.modelo.dto.UserDTO;
import com.indracompany.selecaojava.persistencia.modelo.entidade.Usuario;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   19 de abr de 2020
 */
public class UsuarioMapper {

	private UsuarioMapper() {}

	public static UserDTO toDTO(Usuario user) {

		UserDTO retorno = new UserDTO();
		retorno.setId(user.getCodigo());
		retorno.setEmail(user.getEmail());

		return retorno;
	}

	public static Usuario toEntity(UserDTO userDTO) {

		Usuario retorno = new Usuario();
		retorno.setCodigo(userDTO.getId());
		retorno.setEmail(userDTO.getEmail());
		retorno.setSenha(userDTO.getPassword());
		retorno.setConfirmarSenha(userDTO.getConfirmPassword());
		retorno.setSenhaAtual(userDTO.getCurrentPassword());

		return retorno;
	}

}