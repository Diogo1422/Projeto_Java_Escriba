package com.example.Escriba.infrastructure.persistence;

import com.example.Escriba.domain.model.AtribuicaoCartorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtribuicaoCartorioRepository extends JpaRepository<AtribuicaoCartorio, String> {
}
