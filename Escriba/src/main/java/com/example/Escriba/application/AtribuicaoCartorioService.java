package com.example.Escriba.application;

import com.example.Escriba.application.dto.AtribuicaoCartorioDTO;
import com.example.Escriba.domain.model.AtribuicaoCartorio;

import java.util.List;
import java.util.Optional;

public interface AtribuicaoCartorioService {
    List<AtribuicaoCartorioDTO> listarTodasAtribuicoes();

    Optional<AtribuicaoCartorioDTO> buscarAtribuicaoPorId(String id);

    AtribuicaoCartorioDTO criarAtribuicao(AtribuicaoCartorioDTO atribuicaoCartorioDTO);

    AtribuicaoCartorioDTO atualizarAtribuicao(String id, AtribuicaoCartorioDTO atribuicaoCartorioDTO);

    void excluirAtribuicao(String id);


}
