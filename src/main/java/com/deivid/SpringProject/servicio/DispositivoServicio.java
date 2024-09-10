package com.deivid.SpringProject.servicio;

import com.deivid.SpringProject.modelo.Dispositivo;
import com.deivid.SpringProject.repositorio.IDispositivoRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DispositivoServicio implements IDispositivoServicio {

    @Autowired
    private IDispositivoRepositorio dispositivoRepositorio;
    
    // Método para mostrar todos los dispositivos de la base de datos
    @Override
    public List<Dispositivo> MostrarTodosDispositivos() {
        return dispositivoRepositorio.findAll();
    }

    @Override
    public Dispositivo MostrarDispositivoID(Integer idDispositivo) {
        return dispositivoRepositorio.findById(idDispositivo).orElse(null);
    }

    @Override
    public void IngresarDispositivo(Dispositivo dispositivo) {
        dispositivoRepositorio.save(dispositivo);
    }

    @Override
    public void EliminarDispositivo(Dispositivo dispositivo) {
        dispositivoRepositorio.delete(dispositivo);
    }
}
