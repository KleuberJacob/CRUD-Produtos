package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoDtoResponse {

    private String sku;
    private String nome;
    private String descricao;
    private Double preco;

}
