package com.secondhand.backend.services;

import com.secondhand.backend.config.JWTService;
import com.secondhand.backend.dtos.*;
import com.secondhand.backend.entities.Usuario;
import com.secondhand.backend.entities.RolUsuario;
import com.secondhand.backend.entities.TipoUsuario;
import com.secondhand.backend.repository.TipoUsuarioRepository;
import com.secondhand.backend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService {
    @Autowired
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TipoUsuarioRepository tipoUsuarioRepository;
    @Autowired

    ModelMapper modelMapper = new ModelMapper();

    public List<Usuario> find() {
        return usuarioRepository.findAll();
    }


    public List<Usuario> findByRol(Integer rol) {
        RolUsuario rolUsuario = rol == 1 ? RolUsuario.ROLE_ADMIN : RolUsuario.ROLE_USER;
        return usuarioRepository.findAllByRol(rolUsuario);
    }

    public AuthResponse create(CrearUsuarioDto crearUsuario) {
        TipoUsuario tipoUsuario = tipoUsuarioRepository.findById(crearUsuario.getId_tipo_usuario())
            .orElseThrow(() -> new RuntimeException("TipoUsuario no encontrado"));
        
        String encodedPassword = passwordEncoder.encode(crearUsuario.getContrasena());
        System.out.println("contraseña codificada: "+ encodedPassword);
        Usuario newusuario = Usuario.builder()
                .usuario(crearUsuario.getUsuario())
                .tipoUsuario(tipoUsuario)
                .nombre(crearUsuario.getNombre())
                .apellido(crearUsuario.getApellido())
                .email(crearUsuario.getEmail())
                .contrasena(encodedPassword)
                .rol(RolUsuario.ROLE_USER)
                .build();
        System.out.println(newusuario);
        usuarioRepository.save(newusuario);
        String jwtToken = jwtService.generateToken(newusuario);
        return AuthResponse.builder().token(jwtToken).build();
    }

    public AuthResponse login(IdentificarUsuarioDto identificarUsuario) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        identificarUsuario.getEmail(),
                        identificarUsuario.getContrasena()
                )
        );
        Usuario usuario = usuarioRepository.findByUsuario(identificarUsuario.getEmail()).orElseThrow();
        if (usuario.getRol() != identificarUsuario.getRol()) {
            throw new AuthenticationException("Usuario no autorizado") {
            };
        }
        String jwtToken = jwtService.generateToken(usuario);
        AuthResponse authResponse = AuthResponse.builder()
                .token(jwtToken)
                // .rol(usuario.getRol())
                .build();
        return authResponse;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioBuscado = usuarioRepository.findByUsuario(username);
        if (usuarioBuscado.isPresent()){
            return usuarioBuscado.get();
        }else{
            throw new UsernameNotFoundException("No se encontro un usuario con ese correo");
        }
    }

    public Usuario currentUser(Authentication authentication) {
        Usuario usuario = (Usuario) authentication.getPrincipal();
        return usuario;
    }

    public void updateRole(Long id){
        Optional<Usuario> usuarioBuscado = usuarioRepository.findById(id);
        if (usuarioBuscado.isPresent()){
            Usuario usuario = usuarioBuscado.get();
            if(usuario.getRol() == RolUsuario.ROLE_USER){
                usuario.setRol(RolUsuario.ROLE_ADMIN);
                usuarioRepository.save(usuario);
            } else {
                usuario.setRol(RolUsuario.ROLE_USER);
                usuarioRepository.save(usuario);
            }
        }
    }

    public UserResponse usuarioARespuestaUsuario(Usuario usuario){
        UserResponse userResponse = new UserResponse();
        userResponse.setId(usuario.getId());
        userResponse.setNombre(usuario.getNombre());
        userResponse.setApellido(usuario.getApellido());
        userResponse.setEmail(usuario.getEmail());
        return userResponse;
    }
}
