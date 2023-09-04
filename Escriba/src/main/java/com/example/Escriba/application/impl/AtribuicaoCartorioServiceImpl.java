package com.example.Escriba.application.impl;

import com.example.Escriba.application.AtribuicaoCartorioService;
import com.example.Escriba.application.dto.AtribuicaoCartorioDTO;
import com.example.Escriba.application.exceptions.AtribuicaoCartorioNotFoundException;
import com.example.Escriba.domain.model.AtribuicaoCartorio;
import com.example.Escriba.infrastructure.persistence.AtribuicaoCartorioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AtribuicaoCartorioServiceImpl implements AtribuicaoCartorioService {

    private AtribuicaoCartorioRepository atribuicaoCartorioRepository;

    @Autowired
    public void AtribuicaoCartorioService(AtribuicaoCartorioRepository atribuicaoCartorioRepository) {
        this.atribuicaoCartorioRepository = atribuicaoCartorioRepository;
    }

    @Override
    public List<AtribuicaoCartorioDTO> listarTodasAtribuicoes() {
        return atribuicaoCartorioRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AtribuicaoCartorioDTO> buscarAtribuicaoPorId(String id) {
        return atribuicaoCartorioRepository.findById(id)
                .map(this::convertToDTO);
    }

    @Override
    public AtribuicaoCartorioDTO criarAtribuicao(AtribuicaoCartorioDTO atribuicaoCartorioDTO) {
        AtribuicaoCartorio atribuicaoCartorio = convertToEntity(atribuicaoCartorioDTO);
        atribuicaoCartorio = atribuicaoCartorioRepository.save(atribuicaoCartorio);
        return convertToDTO(atribuicaoCartorio);
    }

    @Override
    public AtribuicaoCartorioDTO atualizarAtribuicao(String id, AtribuicaoCartorioDTO atribuicaoCartorioDTO) {
        AtribuicaoCartorio atribuicaoExistente = atribuicaoCartorioRepository.findById(id)
                .orElseThrow(() -> new AtribuicaoCartorioNotFoundException("Atribuição do cartório não encontrada"));

        BeanUtils.copyProperties(atribuicaoCartorioDTO, atribuicaoExistente, "id");

        atribuicaoExistente = atribuicaoCartorioRepository.save(atribuicaoExistente);

        return convertToDTO(atribuicaoExistente);
    }

    @Override
    public void excluirAtribuicao(String id) {
        atribuicaoCartorioRepository.deleteById(id);
    }

    private AtribuicaoCartorioDTO convertToDTO(AtribuicaoCartorio atribuicaoCartorio) {
        return new AtribuicaoCartorioDTO(atribuicaoCartorio.getId(), atribuicaoCartorio.getNome(), atribuicaoCartorio.isSituacao());
    }

    private AtribuicaoCartorio convertToEntity(AtribuicaoCartorioDTO atribuicaoCartorioDTO) {
        AtribuicaoCartorio atribuicaoCartorio = new AtribuicaoCartorio();
        atribuicaoCartorio.setId(atribuicaoCartorioDTO.getId());
        atribuicaoCartorio.setNome(atribuicaoCartorioDTO.getNome());
        atribuicaoCartorio.setSituacao(atribuicaoCartorioDTO.isSituacao());
        return atribuicaoCartorio;
    }
}

