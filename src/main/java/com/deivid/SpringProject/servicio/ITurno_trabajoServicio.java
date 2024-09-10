package com.deivid.SpringProject.servicio;

import com.deivid.SpringProject.modelo.Turno_trabajo;
import java.util.List;

// Interfaz para la entidad Turno_trabajo
public interface ITurno_trabajoServicio {

    // Muestra todos los turnos de trabajo
    List<Turno_trabajo> MostrarTodosTurnos();
    
    // Muestra un turno de trabajo por su ID
    Turno_trabajo MostrarTurnoID(Integer idTurno);
    
    // Ingresa un nuevo turno de trabajo
    void IngresarTurno(Turno_trabajo turno);

    // Elimina un turno de trabajo
    void EliminarTurno(Turno_trabajo turno);
}
