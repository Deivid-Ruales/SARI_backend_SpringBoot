package com.deivid.SpringProject.servicio;

import com.deivid.SpringProject.modelo.Historial_dispositivo;
import com.deivid.SpringProject.repositorio.IHistorial_dispositivoRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Historial_dispositivoServicio implements IHistorial_dispositivoServicio {

    @Autowired
    private IHistorial_dispositivoRepositorio historialDispositivoRepositorio;
    
    // Método para mostrar todos los historiales de dispositivos de la base de datos
    @Override
    public List<Historial_dispositivo> MostrarTodosHistoriales() {
        return historialDispositivoRepositorio.findAll();
    }

    @Override
    public Historial_dispositivo MostrarHistorialID(Integer idHistorial) {
        return historialDispositivoRepositorio.findById(idHistorial).orElse(null);
    }

    @Override
    public void IngresarHistorial(Historial_dispositivo historial) {
        historialDispositivoRepositorio.save(historial);
    }

    @Override
    public void EliminarHistorial(Historial_dispositivo historial) {
        historialDispositivoRepositorio.delete(historial);
    }
}
