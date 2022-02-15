package com.br.felipe.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.felipe.models.Book;

@RestController
@RequestMapping(value = "book-service")
public class BookController {
	
	@Autowired
	private Environment environment;
	
	@GetMapping(value = "/{id}/{currency}")
	public Book findBook(
			@PathVariable(value = "id") Long id,
			@PathVariable(value = "currency") String currency
			) {
		var port = environment.getProperty("local.server.port");
		return new Book(1L,"Nigel Poulton", "Docker Deep Dive", new Date(), Double.valueOf(13.7), currency, port);
	}

}
