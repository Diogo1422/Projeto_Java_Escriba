package com.example.Escriba.infrastructure.persistence;

import com.example.Escriba.domain.model.Cartorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartorioRepository extends JpaRepository<Cartorio, Integer> {
}

