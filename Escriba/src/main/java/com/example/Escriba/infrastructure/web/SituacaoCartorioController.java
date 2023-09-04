package com.example.Escriba.infrastructure.web;

import com.example.Escriba.application.SituacaoCartorioService;
import com.example.Escriba.application.exceptions.SituacaoCartorioNotFoundException;
import com.example.Escriba.domain.model.SituacaoCartorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/situacoes-cartorio")
public class SituacaoCartorioController {

    private final SituacaoCartorioService situacaoCartorioService;

    @Autowired
    public SituacaoCartorioController(SituacaoCartorioService situacaoCartorioService) {
        this.situacaoCartorioService = situacaoCartorioService;
    }

    @GetMapping
    public List<SituacaoCartorio> listarTodasSituacoes() {
        return situacaoCartorioService.listarTodasSituacoes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SituacaoCartorio> buscarSituacaoPorId(@PathVariable String id) {
        return situacaoCartorioService.buscarSituacaoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SituacaoCartorio> criarSituacao(@Valid @RequestBody SituacaoCartorio situacaoCartorio) {
        SituacaoCartorio novaSituacao = situacaoCartorioService.criarSituacao(situacaoCartorio);
        return ResponseEntity.ok(novaSituacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SituacaoCartorio> atualizarSituacao(
            @PathVariable String id,
            @Valid @RequestBody SituacaoCartorio situacaoCartorio
    ) {
        try {
            SituacaoCartorio situacaoAtualizada = situacaoCartorioService.atualizarSituacao(id, situacaoCartorio);
            return ResponseEntity.ok(situacaoAtualizada);
        } catch (SituacaoCartorioNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirSituacao(@PathVariable String id) {
        try {
            situacaoCartorioService.excluirSituacao(id);
            return ResponseEntity.ok().build();
        } catch (SituacaoCartorioNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

