package com.example.demo.converter;

import com.example.demo.domain.Produto;
import com.example.demo.dto.ProdutoDTORequest;
import com.example.demo.dto.ProdutoDTOResponse;
import org.springframework.stereotype.Component;

@Component
public class ProdutoConverter {

    public ProdutoDTOResponse entityToDto(Produto produto) {
        ProdutoDTOResponse dto = new ProdutoDTOResponse();
        dto.setSku(produto.getSku());
        dto.setNome(produto.getNome());
        dto.setDescricao(produto.getDescricao());
        dto.setPreco(produto.getPreco());
        return dto;
    }

    public Produto dtoToEntity(ProdutoDTORequest produtoDtoRequest) {
        Produto entity = new Produto();
        entity.setSku(produtoDtoRequest.getSku());
        entity.setNome(produtoDtoRequest.getNome());
        entity.setDescricao(produtoDtoRequest.getDescricao());
        entity.setPreco(produtoDtoRequest.getPreco());
        entity.setQuantidade(produtoDtoRequest.getQuantidade());
        return entity;
    }
}
