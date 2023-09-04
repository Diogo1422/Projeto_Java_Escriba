package com.example.Escriba.application.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class CartorioDTO {

    @Positive(message = "O ID deve ser maior que zero")
    private int id;

    @NotBlank(message = "O Nome é obrigatório")
    @Size(max = 150, message = "O Nome deve ter no máximo 150 caracteres")
    private String nome;

    @Size(max = 250, message = "A Observação deve ter no máximo 250 caracteres")
    private String observacao;

    @NotNull(message = "A Situação do Cartório é obrigatória")
    private boolean situacao;

    @Valid
    @NotNull(message = "Pelo menos uma atribuição é obrigatória")
    private List<AtribuicaoCartorioDTO> atribuicoes;
}

