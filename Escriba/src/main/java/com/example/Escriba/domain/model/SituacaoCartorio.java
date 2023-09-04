package com.example.Escriba.domain.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class SituacaoCartorio {

    @Id
    @NotBlank(message = "O ID é obrigatório")
    @Size(max = 20, message = "O ID deve ter no máximo 20 caracteres")
    private String id;

    @NotBlank(message = "O Nome é obrigatório")
    @Size(max = 50, message = "O Nome deve ter no máximo 50 caracteres")
    private String nome;

    // Construtores, getters e setters
}
