package com.example.usuario.controller;

import com.example.usuario.entity.Usuario;
import com.example.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/perfil")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getPerfil(Authentication authentication) {
        try {
            Usuario usuario = usuarioService.findByUsername(authentication.getName());
            return ResponseEntity.ok(Map.of("usuario", usuario));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/perfil")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> updatePerfil(Authentication authentication, @RequestBody UpdatePerfilRequest request) {
        try {
            Usuario usuario = usuarioService.updatePerfil(authentication.getName(), request.getNombre(), request.getAvatar());
            return ResponseEntity.ok(Map.of("message", "Perfil actualizado", "usuario", usuario));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/roles")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getRoles() {
        try {
            return ResponseEntity.ok(Map.of("roles", usuarioService.getAllRoles()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    public static class UpdatePerfilRequest {
        private String nombre;
        private String avatar;

        // Getters and Setters
        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }
        public String getAvatar() { return avatar; }
        public void setAvatar(String avatar) { this.avatar = avatar; }
    }
}
