package com.br.felipe.controllers;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.br.felipe.models.Book;
import com.br.felipe.proxy.CambioProxy;
import com.br.felipe.repositories.BookRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Book endpoints")
@RestController
@RequestMapping(value = "/book-service")
public class BookController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CambioProxy proxy;
	
	private Logger logger = LoggerFactory.getLogger(BookController.class);
			
	@Operation(summary = "find a specific book by your id")
	@GetMapping(value = "/{id}/{currency}")
	public ResponseEntity<Book > findbook(
			@PathVariable(value = "id") Long id,
			@PathVariable(value = "currency") String currency
			) {
		logger.info("Request to findBook is received!!!");
		
		var book = repository.getById(id);

		
		var cambio = proxy.getCambio(book.getPrice(), "USD", currency);

		var port = environment.getProperty("local.server.port");
		book.setEnvironment(port);
		book.setPrice(cambio.getConvertedValue());
		return new ResponseEntity<Book>(book, HttpStatus.OK);
	}
	@Operation(summary = "Get all books")
	@GetMapping(value = "/books/{currency}")
	@Retry(name = "books", fallbackMethod = "fallback_books")
	public ResponseEntity<List<Book>> findAllBooks(@PathVariable(value = "currency") String currency){
		
		logger.info("Request to findAllBook is received!!!");
		List<Book> books = repository.findAll();
		List<Book> finalBooks= new ArrayList<>(); 
		for(Book book: books) {
			var cambio = proxy.getCambio(book.getPrice(), "USD", currency);
			var port = environment.getProperty("local.server.port");
			book.setEnvironment(port);
			book.setPrice(cambio.getConvertedValue());
			finalBooks.add(book);
		}
		
		
		return new ResponseEntity<List<Book>>(finalBooks, HttpStatus.OK);
	}
	
	public ResponseEntity<String> fallback_books(Exception ex){
		List<String> books = new ArrayList<>();
		books.add("ERRO");
		return new ResponseEntity<String>("<h1>Erro</h1>", HttpStatus.INTERNAL_SERVER_ERROR);
	}


}
