package com.indracompany.selecaojava.recurso.rest.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.indracompany.selecaojava.recurso.rest.Endpoint;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   18 de abr de 2020
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/historico-precos")
public class HistoricoPrecoEndpoint implements Endpoint {

	@GetMapping
	public ResponseEntity<String> getlist() {
		return new ResponseEntity<>("Eh apenas um teste", HttpStatus.OK);
	}

}