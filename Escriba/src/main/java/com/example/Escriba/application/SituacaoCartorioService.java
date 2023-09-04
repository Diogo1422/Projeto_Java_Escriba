package com.example.Escriba.application;

import com.example.Escriba.domain.model.SituacaoCartorio;

import java.util.List;
import java.util.Optional;

public interface SituacaoCartorioService {
    List<SituacaoCartorio> listarTodasSituacoes();

    Optional<SituacaoCartorio> buscarSituacaoPorId(String id);

    SituacaoCartorio criarSituacao(SituacaoCartorio situacaoCartorio);
    SituacaoCartorio atualizarSituacao(String id, SituacaoCartorio situacaoCartorio);
    void excluirSituacao(String id);
}
