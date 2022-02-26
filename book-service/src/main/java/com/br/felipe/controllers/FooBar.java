package com.br.felipe.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/book-service")
public class FooBar {
	
	private Logger logger = LoggerFactory.getLogger(BookController.class);

	
	@GetMapping("/foo-bar")
	@Retry(name = "default", fallbackMethod = "fallback")
	public String fooBar() {
		logger.info("Request to foobar is received");
		var response = new RestTemplate().getForEntity("http://localhost:8080/foo-bar", String.class);
		return response.getBody();
	}
	
	public String fallback(Exception ex) {
		
		return "<h1>OPS, AN ERROR</h1>";
		
	}

}
