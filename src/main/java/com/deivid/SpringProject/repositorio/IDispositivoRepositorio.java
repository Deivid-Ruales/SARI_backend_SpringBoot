package com.deivid.SpringProject.repositorio;

import com.deivid.SpringProject.modelo.Dispositivo;
import org.springframework.data.jpa.repository.JpaRepository;

// Repositorio para la entidad Dispositivo
public interface IDispositivoRepositorio extends JpaRepository<Dispositivo, Integer> {
    
}
