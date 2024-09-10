package com.deivid.SpringProject.servicio;

import com.deivid.SpringProject.modelo.Dispositivo;
import java.util.List;

// Interfaz para la entidad Dispositivo
public interface IDispositivoServicio {

    // Muestra todos los dispositivos
    List<Dispositivo> MostrarTodosDispositivos();
    
    // Muestra un dispositivo por su ID
    Dispositivo MostrarDispositivoID(Integer idDispositivo);
    
    // Ingresa un nuevo dispositivo
    void IngresarDispositivo(Dispositivo dispositivo);

    // Elimina un dispositivo
    void EliminarDispositivo(Dispositivo dispositivo);
}
