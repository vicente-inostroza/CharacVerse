package com.example.personajes.controller;

import com.example.personajes.model.Personaje;
import com.example.personajes.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/personajes")
@CrossOrigin(origins = "*")
public class PersonajeController {

    @Autowired
    private PersonajeService personajeService;

    @GetMapping
    public List<Personaje> obtenerTodosLosPersonajes() {
        return personajeService.obtenerTodosLosPersonajes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personaje> obtenerPersonajePorId(@PathVariable Long id) {
        Optional<Personaje> personaje = personajeService.obtenerPersonajePorId(id);
        return personaje.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar")
    public List<Personaje> buscarPersonajesPorNombre(@RequestParam String nombre) {
        return personajeService.buscarPersonajesPorNombre(nombre);
    }

    @PostMapping
    public Personaje crearPersonaje(@RequestBody Personaje personaje) {
        return personajeService.guardarPersonaje(personaje);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Personaje> actualizarPersonaje(@PathVariable Long id, @RequestBody Personaje personajeDetalles) {
        Optional<Personaje> personajeExistente = personajeService.obtenerPersonajePorId(id);
        if (personajeExistente.isPresent()) {
            Personaje personaje = personajeExistente.get();
            personaje.setNombre(personajeDetalles.getNombre());
            personaje.setLore(personajeDetalles.getLore());
            personaje.setImagen(personajeDetalles.getImagen());
            personaje.setJuego(personajeDetalles.getJuego());
            personaje.setGenero(personajeDetalles.getGenero());
            Personaje personajeActualizado = personajeService.guardarPersonaje(personaje);
            return ResponseEntity.ok(personajeActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPersonaje(@PathVariable Long id) {
        personajeService.eliminarPersonaje(id);
        return ResponseEntity.noContent().build();
    }
}
