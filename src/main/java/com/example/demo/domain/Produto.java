package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "produto")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "id_sku")
    private String sku;
    @Column(name = "nome")
    private String nome;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "preco")
    private Double preco;
    @Column(name = "quantidade")
    private Integer quantidade;

}


