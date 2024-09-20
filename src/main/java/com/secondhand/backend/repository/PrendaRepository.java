package com.secondhand.backend.repository;

import com.secondhand.backend.entities.Prenda;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrendaRepository extends JpaRepository<Prenda, Long> {
}
