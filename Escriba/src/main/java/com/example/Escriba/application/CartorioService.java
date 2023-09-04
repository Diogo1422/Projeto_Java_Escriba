package com.example.Escriba.application;

import com.example.Escriba.application.dto.CartorioDTO;

import java.util.List;
import java.util.Optional;

public interface CartorioService {
    List<CartorioDTO> listarTodosCartorios();

    Optional<CartorioDTO> buscarCartorioPorId(int id);

    CartorioDTO criarCartorio(CartorioDTO cartorioDTO);

    CartorioDTO atualizarCartorio(int id, CartorioDTO cartorioDTO);

    void excluirCartorio(int id);
}
