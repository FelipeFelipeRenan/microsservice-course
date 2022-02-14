package com.br.felipe.controllers;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.felipe.model.Cambio;

@RestController
@RequestMapping(value = "cambio-service")
public class CambioController {
	
	@Autowired
	private Environment environment;
	
	@GetMapping(value = "/{amount}/{from}/{to}")
	public Cambio getCambio(
			@PathVariable("amount") BigDecimal amount,
			@PathVariable("from") String from,
			@PathVariable("to") String to) {
		
		var port = environment.getProperty("local.server.port");
		return new Cambio(1L, from, to, BigDecimal.ONE, BigDecimal.ONE, port );
	}

}
