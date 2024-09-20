package com.secondhand.backend.repository;

import com.secondhand.backend.entities.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Long> {
    // Aquí puedes añadir métodos personalizados si los necesitas
}
