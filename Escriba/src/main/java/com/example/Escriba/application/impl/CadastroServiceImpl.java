package com.example.Escriba.application.impl;

import com.example.Escriba.application.CadastroService;
import com.example.Escriba.application.dto.CadastroDTO;
import com.example.Escriba.application.exceptions.CadastroNotFoundException;
import com.example.Escriba.application.exceptions.DuplicateIdException;
import com.example.Escriba.application.exceptions.DuplicateNameException;
import com.example.Escriba.application.exceptions.ReferentialIntegrityException;
import com.example.Escriba.domain.model.Cadastro;
import com.example.Escriba.domain.repository.CadastroRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CadastroServiceImpl implements CadastroService {
    @Autowired
    private final CadastroRepository cadastroRepository;

    public CadastroServiceImpl(CadastroRepository cadastroRepository) {
        this.cadastroRepository = cadastroRepository;
    }

    @Override
    public CadastroDTO criarCadastro(CadastroDTO cadastroDTO) {
        validarNomeDuplicado(null, cadastroDTO.getNome()); // Chamada para validar duplicação
        validarIdDuplicado(cadastroDTO.getId()); // Chamada para validar duplicação de ID

        Cadastro cadastro = new Cadastro();
        BeanUtils.copyProperties(cadastroDTO, cadastro);
        cadastro = cadastroRepository.save(cadastro);

        CadastroDTO responseDTO = new CadastroDTO();
        BeanUtils.copyProperties(cadastro, responseDTO);
        return responseDTO;
    }

    @Override
    public CadastroDTO atualizarCadastro(Long id, CadastroDTO cadastroDTO) {
        validarNomeDuplicado(id, cadastroDTO.getNome()); // Chamada para validar duplicação

        Cadastro cadastroExistente = buscarCadPorId(id);

        cadastroExistente.setNome(cadastroDTO.getNome());

        cadastroRepository.save(cadastroExistente);

        return cadastroDTO;
    }

    private void validarNomeDuplicado(Long id, String nome) {
        Cadastro cadastroExistente = cadastroRepository.findByNome(nome);
        if (cadastroExistente != null && !cadastroExistente.getId().equals(id)) {
            throw new DuplicateNameException("Nome já informado no registro com código " + cadastroExistente.getId() + ".");
        }
    }
    private void validarIdDuplicado(Long id) {
        if (cadastroRepository.findById(id).isPresent()) {
            throw new DuplicateIdException("Registro já cadastrado.");
        }
    }

    @Override
    public void excluirCadastro(Long id) {
        try {
            Cadastro cadastro = buscarCadPorId(id);
            cadastroRepository.delete(cadastro);
        } catch (EmptyResultDataAccessException e) {
            // Lidar com o caso em que o registro não foi encontrado.
            throw new CadastroNotFoundException("Cadastro não encontrado");
        } catch (DataIntegrityViolationException e) {
            // Lidar com a violação de integridade referencial.
            throw new ReferentialIntegrityException("Registro utilizado em outro cadastro.");
        }
    }

    @Override
    public CadastroDTO buscarCadastroPorId(Long id) {
        Cadastro cadastro = cadastroRepository.findById(id)
                .orElseThrow();

        CadastroDTO cadastroDTO = new CadastroDTO();
        BeanUtils.copyProperties(cadastro, cadastroDTO);

        return cadastroDTO;
    }


    private Cadastro buscarCadPorId(Long id) {
        Cadastro cadastro = cadastroRepository.findById(id)
                .orElseThrow();

        CadastroDTO cadastroDTO = new CadastroDTO();
        BeanUtils.copyProperties(cadastro, cadastroDTO);

        return cadastro;
    }

    @Override
    public Page<CadastroDTO> buscarTodosCadastros(Pageable pageable) {
        Page<Cadastro> cadastroPage = cadastroRepository.findAll(pageable);

        return cadastroPage.map(cadastro -> {
            CadastroDTO cadastroDTO = new CadastroDTO();
            BeanUtils.copyProperties(cadastro, cadastroDTO);
            return cadastroDTO;
        });
    }
}