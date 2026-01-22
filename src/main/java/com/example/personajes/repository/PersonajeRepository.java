package com.example.personajes.repository;

import com.example.personajes.entity.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonajeRepository extends JpaRepository<Personaje, Long> {

    List<Personaje> findByNombreContainingIgnoreCase(String nombre);

    @Query("SELECT p FROM Personaje p WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Personaje> buscarPorNombre(@Param("nombre") String nombre);
}
