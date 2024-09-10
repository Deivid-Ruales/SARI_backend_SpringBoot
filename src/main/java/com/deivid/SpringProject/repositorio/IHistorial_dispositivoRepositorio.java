package com.deivid.SpringProject.repositorio;

import com.deivid.SpringProject.modelo.Historial_dispositivo;
import org.springframework.data.jpa.repository.JpaRepository;

// Repositorio para la entidad Historial_dispositivo
public interface IHistorial_dispositivoRepositorio extends JpaRepository<Historial_dispositivo, Integer> {
    
}
