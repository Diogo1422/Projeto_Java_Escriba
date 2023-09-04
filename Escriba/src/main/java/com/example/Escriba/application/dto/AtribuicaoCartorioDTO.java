package com.example.Escriba.application.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class AtribuicaoCartorioDTO {

    private String id;
    private String nome;
    private boolean situacao = true; // Valor padrão TRUE

    public AtribuicaoCartorioDTO(String id, String nome, boolean situacao) {
        this.id = id;
        this.nome = nome;
        this.situacao = situacao;
    }

}


