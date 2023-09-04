package com.example.Escriba.infrastructure.web;

import com.example.Escriba.application.AtribuicaoCartorioService;
import com.example.Escriba.application.dto.AtribuicaoCartorioDTO;
import com.example.Escriba.application.exceptions.AtribuicaoCartorioNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/atribuicoes-cartorio")
public class AtribuicaoCartorioController {

    private final AtribuicaoCartorioService atribuicaoCartorioService;

    @Autowired
    public AtribuicaoCartorioController(AtribuicaoCartorioService atribuicaoCartorioService) {
        this.atribuicaoCartorioService = atribuicaoCartorioService;
    }

    @GetMapping
    public List<AtribuicaoCartorioDTO> listarTodasAtribuicoes() {
        return atribuicaoCartorioService.listarTodasAtribuicoes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtribuicaoCartorioDTO> buscarAtribuicaoPorId(@PathVariable String id) {
        return atribuicaoCartorioService.buscarAtribuicaoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AtribuicaoCartorioDTO> criarAtribuicao(@Valid @RequestBody AtribuicaoCartorioDTO atribuicaoCartorioDTO) {
        AtribuicaoCartorioDTO novaAtribuicao = atribuicaoCartorioService.criarAtribuicao(atribuicaoCartorioDTO);
        return ResponseEntity.ok(novaAtribuicao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtribuicaoCartorioDTO> atualizarAtribuicao(
            @PathVariable String id,
            @Valid @RequestBody AtribuicaoCartorioDTO atribuicaoCartorioDTO
    ) {
        try {
            AtribuicaoCartorioDTO atribuicaoAtualizada = atribuicaoCartorioService.atualizarAtribuicao(id, atribuicaoCartorioDTO);
            return ResponseEntity.ok(atribuicaoAtualizada);
        } catch (AtribuicaoCartorioNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirAtribuicao(@PathVariable String id) {
        try {
            atribuicaoCartorioService.excluirAtribuicao(id);
            return ResponseEntity.ok().build();
        } catch (AtribuicaoCartorioNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

