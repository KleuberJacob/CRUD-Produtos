package com.example.demo.converter;

import com.example.demo.domain.Produto;
import com.example.demo.dto.ProdutoDtoRequest;
import com.example.demo.dto.ProdutoDtoResponse;
import org.springframework.stereotype.Component;

@Component
public class ProdutoConverter {

    public ProdutoDtoResponse entityToDto(Produto produto) {
        ProdutoDtoResponse dto = new ProdutoDtoResponse();
        dto.setSku(produto.getSku());
        dto.setNome(produto.getNome());
        dto.setDescricao(produto.getDescricao());
        dto.setPreco(produto.getPreco());
        return dto;
    }

    public Produto dtoToEntity(ProdutoDtoRequest produtoDtoRequest) {
        Produto entity = new Produto();
        entity.setSku(produtoDtoRequest.getSku());
        entity.setNome(produtoDtoRequest.getNome());
        entity.setDescricao(produtoDtoRequest.getDescricao());
        entity.setPreco(produtoDtoRequest.getPreco());
        entity.setQuantidade(produtoDtoRequest.getQuantidade());
        return entity;
    }
}
