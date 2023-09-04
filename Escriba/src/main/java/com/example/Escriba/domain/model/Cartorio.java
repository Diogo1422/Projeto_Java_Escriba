package com.example.Escriba.domain.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
public class Cartorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Positive(message = "O ID deve ser maior que zero")
    private int id;

    @NotBlank(message = "O Nome é obrigatório")
    @Size(max = 150, message = "O Nome deve ter no máximo 150 caracteres")
    private String nome;

    @Size(max = 250, message = "A Observação deve ter no máximo 250 caracteres")
    private String observacao;

    @NotNull(message = "A Situação do Cartório é obrigatória")
    private boolean situacao;

    @ManyToMany
    @JoinTable(
            name = "cartorio_atribuicoes",
            joinColumns = @JoinColumn(name = "cartorio_id"),
            inverseJoinColumns = @JoinColumn(name = "atribuicao_id")
    )
    @NotNull(message = "Pelo menos uma atribuição é obrigatória")
    private List<AtribuicaoCartorio> atribuicoes;
}

