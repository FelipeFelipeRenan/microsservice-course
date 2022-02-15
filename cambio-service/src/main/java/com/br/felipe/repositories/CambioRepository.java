package com.br.felipe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.felipe.model.Cambio;

public interface CambioRepository extends JpaRepository<Cambio, Long> {
	
	Cambio findByFromAndTo(String from, String to);

}
