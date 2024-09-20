package com.secondhand.backend.repository;

import com.secondhand.backend.entities.CategoriaPrenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaPrendaRepository extends JpaRepository<CategoriaPrenda, Long> {
    // Métodos adicionales si son necesarios.
}
