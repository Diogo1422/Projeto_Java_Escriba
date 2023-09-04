package com.example.Escriba.infrastructure.web;

import com.example.Escriba.application.CartorioService;
import com.example.Escriba.application.dto.CartorioDTO;
import com.example.Escriba.application.exceptions.CartorioNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cartorios")
public class CartorioController {

    private final CartorioService cartorioService;

    @Autowired
    public CartorioController(CartorioService cartorioService) {
        this.cartorioService = cartorioService;
    }

    @GetMapping
    public List<CartorioDTO> listarTodosCartorios() {
        return cartorioService.listarTodosCartorios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartorioDTO> buscarCartorioPorId(@PathVariable int id) {
        return cartorioService.buscarCartorioPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CartorioDTO> criarCartorio(@Valid @RequestBody CartorioDTO cartorioDTO) {
        CartorioDTO novoCartorio = cartorioService.criarCartorio(cartorioDTO);
        return ResponseEntity.ok(novoCartorio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartorioDTO> atualizarCartorio(
            @PathVariable int id,
            @Valid @RequestBody CartorioDTO cartorioDTO
    ) {
        try {
            CartorioDTO cartorioAtualizado = cartorioService.atualizarCartorio(id, cartorioDTO);
            return ResponseEntity.ok(cartorioAtualizado);
        } catch (CartorioNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirCartorio(@PathVariable int id) {
        try {
            cartorioService.excluirCartorio(id);
            return ResponseEntity.ok().build();
        } catch (CartorioNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
