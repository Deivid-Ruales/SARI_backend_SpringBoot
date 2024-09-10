package com.deivid.SpringProject.repositorio;

import com.deivid.SpringProject.modelo.Turno_trabajo;
import org.springframework.data.jpa.repository.JpaRepository;

// Repositorio para la entidad Turno_trabajo
public interface ITurno_trabajoRepositorio extends JpaRepository<Turno_trabajo, Integer> {
    
}
