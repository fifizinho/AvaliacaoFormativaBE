package com.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}