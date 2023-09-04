package com.example.Escriba.application;

import com.example.Escriba.application.dto.CadastroDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CadastroService {
    CadastroDTO criarCadastro(CadastroDTO cadastroDTO);

    CadastroDTO atualizarCadastro(Long id, CadastroDTO cadastroDTO);

    void excluirCadastro(Long id);

    CadastroDTO buscarCadastroPorId(Long id);

    Page<CadastroDTO> buscarTodosCadastros(Pageable pageable);
}
