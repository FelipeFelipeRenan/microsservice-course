package com.br.felipe.controllers;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.felipe.models.Book;
import com.br.felipe.proxy.CambioProxy;
import com.br.felipe.repositories.BookRepository;

import io.github.resilience4j.retry.annotation.Retry;


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
	@Retry(name = "default" , fallbackMethod = "fallbackMethod")
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
	@GetMapping(value = "/books/{currency}")
	//@Retry(name = "default" , fallbackMethod = "fallbackMethod")
	public List<Book> findAllBooks(@PathVariable(value = "currency") String currency){
		List<Book> books = repository.findAll();
		List<Book> finalBooks= new ArrayList<>(); 
		for(Book book: books) {
			var cambio = proxy.getCambio(book.getPrice(), "USD", currency);
			book.setPrice(cambio.getConvertedValue());
			finalBooks.add(book);
		}
		
		
		return finalBooks;
	}
	
	public String fallbackMethod(Exception ex){
		return "Error";
	}

}
