package com.secondhand.backend.repository;

import com.secondhand.backend.entities.RolUsuario;
import com.secondhand.backend.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsuario(String usuario);

    List<Usuario> findAllByRol(RolUsuario rol);
}
