package com.example.Escriba.application.impl;

import com.example.Escriba.application.CartorioService;
import com.example.Escriba.application.dto.AtribuicaoCartorioDTO;
import com.example.Escriba.application.dto.CartorioDTO;
import com.example.Escriba.application.exceptions.CartorioNotFoundException;
import com.example.Escriba.domain.model.AtribuicaoCartorio;
import com.example.Escriba.domain.model.Cartorio;
import com.example.Escriba.infrastructure.persistence.CartorioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartorioServiceImpl implements CartorioService {

    private final CartorioRepository cartorioRepository;

    @Autowired
    public CartorioServiceImpl(CartorioRepository cartorioRepository) {
        this.cartorioRepository = cartorioRepository;
    }

    @Override
    public List<CartorioDTO> listarTodosCartorios() {
        return cartorioRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CartorioDTO> buscarCartorioPorId(int id) {
        return cartorioRepository.findById(id)
                .map(this::convertToDTO);
    }

    @Override
    public CartorioDTO criarCartorio(CartorioDTO cartorioDTO) {
        Cartorio cartorio = convertToEntity(cartorioDTO);
        cartorio = cartorioRepository.save(cartorio);
        return convertToDTO(cartorio);
    }

    @Override
    public CartorioDTO atualizarCartorio(int id, CartorioDTO cartorioDTO) {
        Cartorio cartorioExistente = cartorioRepository.findById(id)
                .orElseThrow(() -> new CartorioNotFoundException("Cartório não encontrado"));

        BeanUtils.copyProperties(cartorioDTO, cartorioExistente, "id");

        cartorioExistente = cartorioRepository.save(cartorioExistente);

        return convertToDTO(cartorioExistente);
    }

    @Override
    public void excluirCartorio(int id) {
        cartorioRepository.deleteById(id);
    }

    private CartorioDTO convertToDTO(Cartorio cartorio) {
        CartorioDTO cartorioDTO = new CartorioDTO();
        cartorioDTO.setId(cartorio.getId());
        cartorioDTO.setNome(cartorio.getNome());
        cartorioDTO.setObservacao(cartorio.getObservacao());
        cartorioDTO.setSituacao(cartorio.isSituacao());
        cartorioDTO.setAtribuicoes(
                cartorio.getAtribuicoes().stream()
                        .map(this::convertToDTO)
                        .collect(Collectors.toList())
        );
        return cartorioDTO;
    }

    private AtribuicaoCartorioDTO convertToDTO(AtribuicaoCartorio atribuicao) {
        AtribuicaoCartorioDTO atribuicaoDTO = new AtribuicaoCartorioDTO(atribuicao.getId(), atribuicao.getNome(), atribuicao.isSituacao());
        return atribuicaoDTO;
    }

    private Cartorio convertToEntity(CartorioDTO cartorioDTO) {
        Cartorio cartorio = new Cartorio();
        cartorio.setNome(cartorioDTO.getNome());
        cartorio.setObservacao(cartorioDTO.getObservacao());
        cartorio.setSituacao(cartorioDTO.isSituacao());
        List<AtribuicaoCartorio> atribuicoes = cartorioDTO.getAtribuicoes().stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
        cartorio.setAtribuicoes(atribuicoes);
        return cartorio;
    }

    private AtribuicaoCartorio convertToEntity(AtribuicaoCartorioDTO atribuicaoDTO) {
        AtribuicaoCartorio atribuicao = new AtribuicaoCartorio();
        atribuicao.setNome(atribuicaoDTO.getNome());
        return atribuicao;
    }
}

