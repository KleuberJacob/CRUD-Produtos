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
@RequestMapping("produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService service;

    @PostMapping
    public ResponseEntity<ProdutoDTOResponse> salvar(@Valid @RequestBody ProdutoDTORequest produtoDtoRequest) {
        ProdutoDTOResponse produtoDtoResponse = service.salvarProduto(produtoDtoRequest);
        return new ResponseEntity<>(produtoDtoResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDTOResponse>> buscarProdutos() {
        List<ProdutoDTOResponse> produtos = service.buscarProdutos();
        if (produtos == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ProdutoDTOResponse buscarProduto(@PathVariable Long id) {
        return service.buscarProduto(id);
    }

    @PutMapping
    public ResponseEntity<ProdutoDTOResponse> atualizaProduto(@Valid @RequestBody ProdutoDTORequest produtoDtoRequest) {
        ProdutoDTOResponse produtoDtoResponse = service.atualizarProduto(produtoDtoRequest);
        return ResponseEntity.ok(produtoDtoResponse);
    }

    @DeleteMapping(value="/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void excluir(@PathVariable Long id) throws NaoExisteException {
        service.excluir(id);
    }

}
