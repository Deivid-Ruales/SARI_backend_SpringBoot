package com.deivid.SpringProject.servicio;

import com.deivid.SpringProject.modelo.Turno_trabajo;
import com.deivid.SpringProject.repositorio.ITurno_trabajoRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Turno_trabajoServicio implements ITurno_trabajoServicio {

    @Autowired
    private ITurno_trabajoRepositorio turnoTrabajoRepositorio;
    
    // Método para mostrar todos los turnos de trabajo de la base de datos
    @Override
    public List<Turno_trabajo> MostrarTodosTurnos() {
        return turnoTrabajoRepositorio.findAll();
    }

    @Override
    public Turno_trabajo MostrarTurnoID(Integer idTurno) {
        return turnoTrabajoRepositorio.findById(idTurno).orElse(null);
    }

    @Override
    public void IngresarTurno(Turno_trabajo turno) {
        turnoTrabajoRepositorio.save(turno);
    }

    @Override
    public void EliminarTurno(Turno_trabajo turno) {
        turnoTrabajoRepositorio.delete(turno);
    }
}
