package com.example.demo.controller;

import com.example.demo.dto.ProdutoDTORequest;
import com.example.demo.dto.ProdutoDTOResponse;
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
    public ResponseEntity<ProdutoDTOResponse> salvar(@Valid @RequestBody ProdutoDTORequest produtoDtoRequest) {
        ProdutoDTOResponse produtoDtoResponse = service.salvarProduto(produtoDtoRequest);
        return new ResponseEntity<>(produtoDtoResponse, HttpStatus.CREATED);
    }

    @GetMapping(value="/todos")
    public ResponseEntity<List<ProdutoDTOResponse>> buscarTodosProdutos() {
        List<ProdutoDTOResponse> produtos = service.buscarTodosProdutos();
        return ResponseEntity.ok(produtos);
    }
    @GetMapping(value="/{id}")
    public ProdutoDTOResponse buscarProduto(@PathVariable Long id) {
        return service.buscarProduto(id);
    }

    @PutMapping
    public ResponseEntity<ProdutoDTOResponse> atualizarProduto(@Valid @RequestBody ProdutoDTORequest produtoDtoRequest) {
        ProdutoDTOResponse produtoDtoResponse = service.atualizarProduto(produtoDtoRequest);
        return ResponseEntity.ok(produtoDtoResponse);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) throws NaoExisteException {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

}
