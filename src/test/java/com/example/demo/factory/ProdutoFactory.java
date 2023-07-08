package com.example.demo.factory;

import com.example.demo.domain.Produto;
import com.example.demo.dto.ProdutoDTORequest;
import com.example.demo.dto.ProdutoDTOResponse;

public class ProdutoFactory {

    public static Produto novoProduto() {
        final var produto = new Produto();
        produto.setId(1L);
        produto.setSku("SK1");
        produto.setNome("Notebook");
        produto.setDescricao("Notebook Positivo");
        produto.setPreco(1999.00);
        produto.setQuantidade(5);

        return produto;
    }

    public static ProdutoDTORequest novoProdutoDTORequest() {
        final var produtoDTORequest = new ProdutoDTORequest();
        produtoDTORequest.setSku("SK1");
        produtoDTORequest.setNome("Notebook");
        produtoDTORequest.setDescricao("Notebook Positivo");
        produtoDTORequest.setPreco(1999.00);
        produtoDTORequest.setQuantidade(5);

        return produtoDTORequest;
    }

    public static ProdutoDTOResponse novoProdutoDTOResponse() {
        final var produtoDTOResponse = new ProdutoDTOResponse();
        produtoDTOResponse.setSku("SK1");
        produtoDTOResponse.setNome("Notebook");
        produtoDTOResponse.setDescricao("Notebook Positivo");
        produtoDTOResponse.setPreco(1999.00);

        return produtoDTOResponse;
    }

}
