package com.example.personajes.service;

import com.example.personajes.model.Personaje;
import com.example.personajes.repository.PersonajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonajeService {

    @Autowired
    private PersonajeRepository personajeRepository;

    public List<Personaje> obtenerTodosLosPersonajes() {
        return personajeRepository.findAll();
    }

    public Optional<Personaje> obtenerPersonajePorId(Long id) {
        return personajeRepository.findById(id);
    }

    public List<Personaje> buscarPersonajesPorNombre(String nombre) {
        return personajeRepository.buscarPorNombre(nombre);
    }

    public Personaje guardarPersonaje(Personaje personaje) {
        return personajeRepository.save(personaje);
    }

    public void eliminarPersonaje(Long id) {
        personajeRepository.deleteById(id);
    }
}
