package com.example.Escriba.infrastructure.persistence;

import com.example.Escriba.domain.model.SituacaoCartorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SituacaoCartorioRepository extends JpaRepository<SituacaoCartorio, String> {
}
