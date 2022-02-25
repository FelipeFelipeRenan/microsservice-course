package com.br.felipe.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import reactor.core.publisher.Mono;

@Component
public class LoggingFilter implements GlobalFilter{
	
	private Logger logger = LoggerFactory.getLogger(LoggingFilter.class);
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		logger.info("original request path -> {}", exchange.getRequest().getPath());
		return chain.filter(exchange);
	}

}
