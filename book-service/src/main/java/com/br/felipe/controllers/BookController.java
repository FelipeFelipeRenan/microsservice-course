package com.br.felipe.controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.br.felipe.models.Book;
import com.br.felipe.proxy.CambioProxy;
import com.br.felipe.repositories.BookRepository;
import com.br.felipe.response.Cambio;

@RestController
@RequestMapping(value = "/book-service")
public class BookController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CambioProxy proxy;
	
	@GetMapping(value = "/{id}/{currency}")
	public Book findBook(
			@PathVariable(value = "id") Long id,
			@PathVariable(value = "currency") String currency
			) {
		
		var book = repository.getById(id);
		if (book == null) {
			throw new RuntimeException("Book not Found");
			
		}
		
		var cambio = proxy.getCambio(book.getPrice(), "USD", currency);

		var port = environment.getProperty("local.server.port");
		book.setEnvironment(port);
		book.setPrice(cambio.getConvertedValue());
		return book;
	}

}
