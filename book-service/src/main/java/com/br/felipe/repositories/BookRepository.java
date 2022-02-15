package com.br.felipe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.felipe.models.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
