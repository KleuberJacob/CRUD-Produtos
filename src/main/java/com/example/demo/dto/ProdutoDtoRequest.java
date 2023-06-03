package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoDtoRequest {

    @NotNull(message = "O número de sku deve ser informado.")
    private String sku;
    @NotNull(message = "O nome do produto deve ser informado.")
    private String nome;
    @NotNull(message = "A descrição do produto deve ser informada.")
    private String descricao;
    @NotNull(message = "O valor do produto deve ser informado.")
    private Double preco;
    @NotNull(message = "A quantidade de estoque do produto deve ser informada.")
    private Integer quantidade;

}
