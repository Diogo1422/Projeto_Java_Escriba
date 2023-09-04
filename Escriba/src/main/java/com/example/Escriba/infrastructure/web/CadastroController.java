package com.example.Escriba.infrastructure.web;

import com.example.Escriba.application.CadastroService;
import com.example.Escriba.application.dto.CadastroDTO;
import com.example.Escriba.application.exceptions.CadastroNotFoundException;
import com.example.Escriba.application.exceptions.DuplicateIdException;
import com.example.Escriba.application.exceptions.DuplicateNameException;
import com.example.Escriba.application.exceptions.ReferentialIntegrityException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cadastros")
public class CadastroController {
    private final CadastroService cadastroService;

    public CadastroController(CadastroService cadastroService) {
        this.cadastroService = cadastroService;
    }

    @PostMapping
    public ResponseEntity<?> criarCadastro(@RequestBody CadastroDTO cadastroDTO) {
        try {
            CadastroDTO responseDTO = cadastroService.criarCadastro(cadastroDTO);
            return ResponseEntity.ok(responseDTO);
        } catch (DuplicateNameException | DuplicateIdException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarCadastro(@PathVariable Long id, @RequestBody CadastroDTO cadastroDTO) {
        try {
            cadastroDTO = cadastroService.atualizarCadastro(id, cadastroDTO);
            return ResponseEntity.ok(cadastroDTO);
        } catch (DuplicateNameException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (CadastroNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirCadastro(@PathVariable Long id) {
        try {
            cadastroService.excluirCadastro(id);
            return ResponseEntity.ok("Cadastro excluído com sucesso");
        } catch (ReferentialIntegrityException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (CadastroNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public CadastroDTO buscarCadastroPorId(@PathVariable Long id) {
        return cadastroService.buscarCadastroPorId(id);
    }

    @GetMapping
    public Page<CadastroDTO> buscarTodosCadastros(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return cadastroService.buscarTodosCadastros(Pageable.ofSize(size).withPage(page));
    }
}
