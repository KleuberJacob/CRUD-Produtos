package com.example.demo.controller;

import com.example.demo.dto.ProdutoDtoRequest;
import com.example.demo.dto.ProdutoDtoResponse;
import com.example.demo.exceptions.NaoExisteException;
import com.example.demo.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService service;

    @PostMapping
    public ResponseEntity<ProdutoDtoResponse> salvar(@Valid @RequestBody ProdutoDtoRequest produtoDtoRequest) {
        ProdutoDtoResponse produtoDtoResponse = service.salvarProduto(produtoDtoRequest);
        return new ResponseEntity<>(produtoDtoResponse, HttpStatus.CREATED);
    }

    @GetMapping(value="/todos")
    public ResponseEntity<List<ProdutoDtoResponse>> buscarTodosProdutos() {
        List<ProdutoDtoResponse> produtos = service.buscarTodosProdutos();
        return ResponseEntity.ok(produtos);
    }
    @GetMapping(value="/{id}")
    public ProdutoDtoResponse buscarProduto(@PathVariable Long id) {
        return service.buscarProduto(id);
    }

    @PutMapping
    public ResponseEntity<ProdutoDtoResponse> atualizarProduto(@Valid @RequestBody ProdutoDtoRequest produtoDtoRequest) {
        ProdutoDtoResponse produtoDtoResponse = service.atualizarProduto(produtoDtoRequest);
        return ResponseEntity.ok(produtoDtoResponse);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) throws NaoExisteException {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

}
