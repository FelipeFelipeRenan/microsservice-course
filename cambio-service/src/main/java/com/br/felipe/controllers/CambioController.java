package com.br.felipe.controllers;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.felipe.model.Cambio;
import com.br.felipe.repositories.CambioRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Cambio endpoints ")
@RestController
@RequestMapping(value = "cambio-service")
public class CambioController {
	
	@Autowired
	private Environment environment;
	@Autowired
	private CambioRepository repository;
	
	@Operation(summary = "get cambio value for a currency")
	@GetMapping(value = "/{amount}/{from}/{to}")
	public Cambio getCambio(
			@PathVariable("amount") BigDecimal amount,
			@PathVariable("from") String from,
			@PathVariable("to") String to) {
		
		
		var cambio = repository.findByFromAndTo(from, to);
		if (cambio == null) {
			throw new RuntimeException("Currency Unsupported!");  
		}
		var port = environment.getProperty("local.server.port");
		BigDecimal conversionFactor = cambio.getConversionFactor();
		BigDecimal convertedValue = conversionFactor.multiply(amount);
		cambio.setConvertedValue(convertedValue.setScale(2, RoundingMode.CEILING));
		cambio.setEnviroment(port);
		return cambio;
	}

}
