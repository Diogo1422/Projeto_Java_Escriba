package com.example.Escriba.infrastructure.persistence;

import com.example.Escriba.domain.model.Cadastro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CadastroJpaRepository extends JpaRepository<Cadastro, Long> {
}
