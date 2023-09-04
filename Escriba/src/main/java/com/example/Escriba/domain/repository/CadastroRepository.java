package com.example.Escriba.domain.repository;


import com.example.Escriba.domain.model.Cadastro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CadastroRepository extends JpaRepository<Cadastro, Long> {
    Cadastro findByNome(String nome);
}

