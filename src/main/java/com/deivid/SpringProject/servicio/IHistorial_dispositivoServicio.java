package com.deivid.SpringProject.servicio;

import com.deivid.SpringProject.modelo.Historial_dispositivo;
import java.util.List;

// Interfaz para la entidad Historial_dispositivo
public interface IHistorial_dispositivoServicio {

    // Muestra todos los historiales de dispositivos
    List<Historial_dispositivo> MostrarTodosHistoriales();
    
    // Muestra un historial de dispositivo por su ID
    Historial_dispositivo MostrarHistorialID(Integer idHistorial);
    
    // Ingresa un nuevo historial de dispositivo
    void IngresarHistorial(Historial_dispositivo historial);

    // Elimina un historial de dispositivo
    void EliminarHistorial(Historial_dispositivo historial);
    
    // Muestra historial de un dispositivo por Id de dispositivo
    List<Historial_dispositivo> MostrarHistorialesIdDispositivo(Integer idDispositivo);
    
    // Ingresa un nuevo historial de dispositivo pasando Id de dispositivo
    void IngresarHistorialIdDispositivo(Integer idDispositivo, Historial_dispositivo historial);
}
