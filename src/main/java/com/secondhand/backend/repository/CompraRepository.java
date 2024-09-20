package com.secondhand.backend.repository;

import com.secondhand.backend.entities.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {
    // Métodos adicionales si son necesarios.
}
