package com.secondhand.backend.controllers;

import com.secondhand.backend.dtos.AuthResponse;
import com.secondhand.backend.dtos.CrearUsuarioDto;
import com.secondhand.backend.dtos.IdentificarUsuarioDto;
import com.secondhand.backend.entities.RolUsuario;
import com.secondhand.backend.entities.Usuario;
import com.secondhand.backend.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/username")
    public long currentUserid(Authentication authentication) {
        Usuario usuario = (Usuario) authentication.getPrincipal();
        return usuario.getId();
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> find() {
        return ResponseEntity.ok(usuarioService.find());
    }



    @GetMapping("/roles/{rol}")
    public ResponseEntity<List<Usuario>> findByRol(@PathVariable Integer rol) {
        return ResponseEntity.ok(usuarioService.findByRol(rol));
    }

    @PostMapping
    public ResponseEntity<AuthResponse> create(@RequestBody CrearUsuarioDto crearUsuario){
        return ResponseEntity.ok(usuarioService.create(crearUsuario));
    }

    @PostMapping("/identificar")
    public ResponseEntity<AuthResponse> login(@RequestBody IdentificarUsuarioDto identificarUsuario){
        return ResponseEntity.ok(usuarioService.login(identificarUsuario));
    }

    @PutMapping("/{id}")
    public String updateRole(@PathVariable Long id){
        usuarioService.updateRole(id);
        return "Rol de usuario actualizado";
    }
}
