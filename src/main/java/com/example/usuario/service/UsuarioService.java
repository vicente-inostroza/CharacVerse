package com.example.usuario.service;

import com.example.usuario.entity.Rol;
import com.example.usuario.entity.Usuario;
import com.example.usuario.repository.RolRepository;
import com.example.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario registrarUsuario(String username, String password, String nombre, String avatar, Set<String> rolesNombres) {
        if (usuarioRepository.existsByUsername(username)) {
            throw new RuntimeException("El username ya existe");
        }

        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setPassword(passwordEncoder.encode(password));
        usuario.setNombre(nombre);
        usuario.setAvatar(avatar);

        Set<Rol> roles = new HashSet<>();
        for (String rolNombre : rolesNombres) {
            Rol rol = rolRepository.findByNombre(rolNombre)
                    .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + rolNombre));
            roles.add(rol);
        }
        usuario.setRoles(roles);

        return usuarioRepository.save(usuario);
    }

    public Usuario findByUsername(String username) {
        return usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public Usuario updatePerfil(String username, String nombre, String avatar) {
        Usuario usuario = findByUsername(username);
        usuario.setNombre(nombre);
        usuario.setAvatar(avatar);
        return usuarioRepository.save(usuario);
    }

    public List<Rol> getAllRoles() {
        return rolRepository.findAll();
    }
}
