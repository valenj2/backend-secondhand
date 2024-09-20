package com.secondhand.backend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "tipo_usuarios")
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TipoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "tipo_usuario", length = 20)
    private String id_tipo_usuario;

    @Column(name = "descripcion", length = 50)
    private String descripcion;

    @Column(name = "creado")
    private Timestamp creado;

    @Column(name = "actualizado")
    private Timestamp actualizado;
}
