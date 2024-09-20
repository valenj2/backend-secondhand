package com.secondhand.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CrearUsuarioDto {
    private String usuario;
    private Long id_tipo_usuario;
    private String nombre;
    private String apellido;
    private String email;
    private String contrasena;
}
