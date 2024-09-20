package com.secondhand.backend.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    
    @ManyToOne
    @JoinColumn(name = "id_tipo_usuario")
    private TipoUsuario tipoUsuario;

    @Column
    private String usuario;

    @Column
    private String nombre;


    @Column
    private String apellido;
    @Column
    private String email;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private RolUsuario rol;

    // @Column
    // @JsonIgnore
    // @OneToMany(mappedBy="usuario", cascade = CascadeType.ALL, fetch =
    // FetchType.EAGER)
    // private Set<Reserva> reservas;

    @Column
    private String contrasena;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(rol.name()));
    }

    @Override
    public String getPassword() {
        return contrasena;
    }

    @Override
    public String getUsername() {
        return usuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
