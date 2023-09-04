package com.example.Escriba.application.impl;

import com.example.Escriba.application.SituacaoCartorioService;
import com.example.Escriba.application.exceptions.SituacaoCartorioNotFoundException;
import com.example.Escriba.domain.model.SituacaoCartorio;
import com.example.Escriba.infrastructure.persistence.SituacaoCartorioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SituacaoCartorioServiceImpl implements SituacaoCartorioService {
    private SituacaoCartorioRepository situacaoCartorioRepository;

    @Autowired
    public void SituacaoCartorioService(SituacaoCartorioRepository situacaoCartorioRepository) {
        this.situacaoCartorioRepository = situacaoCartorioRepository;
    }

    @Override
    public List<SituacaoCartorio> listarTodasSituacoes() {
        return situacaoCartorioRepository.findAll();
    }
    @Override
    public Optional<SituacaoCartorio> buscarSituacaoPorId(String id) {
        return situacaoCartorioRepository.findById(id);
    }
    @Override
    public SituacaoCartorio criarSituacao(SituacaoCartorio situacaoCartorio) {
        return situacaoCartorioRepository.save(situacaoCartorio);
    }
    @Override
    public SituacaoCartorio atualizarSituacao(String id, SituacaoCartorio situacaoCartorio) {
        SituacaoCartorio situacaoExistente = situacaoCartorioRepository.findById(id)
                .orElseThrow(() -> new SituacaoCartorioNotFoundException("Situação do cartório não encontrada"));

        BeanUtils.copyProperties(situacaoCartorio, situacaoExistente, "id");

        return situacaoCartorioRepository.save(situacaoExistente);
    }
    @Override
    public void excluirSituacao(String id) {
        situacaoCartorioRepository.deleteById(id);
    }
}
