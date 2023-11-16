package com.projeto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.entities.Produto;
import com.projeto.services.ProdutoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Produtos", description = "API REST DE GERENCIAMENTO DE Produtos")
@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    
    private final ProdutoService produtoService;
    
    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Localiza Produto por ID")
    public ResponseEntity<Produto> getProductById(@PathVariable Long id) {
    	Produto produto = produtoService.getProdutoById(id);
        if (produto != null) {
            return ResponseEntity.ok(produto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/")
    @Operation(summary = "Apresenta todos os Produtos")
    public ResponseEntity<List<Produto>> getAllProdutos() {
        List<Produto> produtos = produtoService.getAllProdutos();
        return ResponseEntity.ok(produtos);
    }
    @PostMapping("/")
    @Operation(summary = "Cadastra um Produto")
    public ResponseEntity<Produto> criarProduto(@RequestBody @Valid Produto produto) {
    	Produto criarProduto = produtoService.salvarProduto(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criarProduto);
    }
   

    @PutMapping("/{id}")
    @Operation(summary = "Altera o Produto")
    public ResponseEntity<Produto> updateProduto(@PathVariable Long id, @RequestBody @Valid Produto produto) {
    	Produto updatedProduto = produtoService.updateProduto(id, produto);
        if (updatedProduto != null) {
            return ResponseEntity.ok(updatedProduto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta o Produto")
    public ResponseEntity<String> deleteProduto(@PathVariable Long id) {
        boolean deleted = produtoService.deleteProduto(id);
        if (deleted) {
        	 return ResponseEntity.ok().body("O produto foi excluído com sucesso.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}