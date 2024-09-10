package com.deivid.SpringProject.controlador;

import com.deivid.SpringProject.modelo.Turno_trabajo;
import com.deivid.Excepcion.ExcepcionRecursoNoEncontrado;
import com.deivid.SpringProject.servicio.ITurno_trabajoServicio;
import com.deivid.SpringProject.servicio.Turno_trabajoServicio;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sari/turnos")
@CrossOrigin(value = "http://localhost:3000/")
public class Turno_trabajoControlador {

    private static final Logger logger = LoggerFactory.getLogger(Turno_trabajoControlador.class);

    @Autowired
    private ITurno_trabajoServicio turnoTrabajoServicio;

    @GetMapping
    public List<Turno_trabajo> MostrarTurnos() {
        var turnos = turnoTrabajoServicio.MostrarTodosTurnos();
        turnos.forEach(turno -> logger.info(turno.toString()));
        return turnos;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno_trabajo> MostrarTurnoId(@PathVariable Integer id) {
        Turno_trabajo turno = turnoTrabajoServicio.MostrarTurnoID(id);
        if (turno == null) {
            throw new ExcepcionRecursoNoEncontrado("No se encontró el Id del turno de trabajo: " + id);
        }
        return ResponseEntity.ok(turno);
    }

    @PostMapping
    public void IngresarTurno(@RequestBody Turno_trabajo turno) {
        logger.info("Turno a ingresar: " + turno);
        turnoTrabajoServicio.IngresarTurno(turno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turno_trabajo> ModificarTurnoId(@PathVariable Integer id, @RequestBody Turno_trabajo turnoNew) {
        Turno_trabajo turno = turnoTrabajoServicio.MostrarTurnoID(id);
        if (turno == null) {
            throw new ExcepcionRecursoNoEncontrado("No se encontró el Id del turno de trabajo: " + id);
        }

        turno.setFecha_hora_inicio(turnoNew.getFecha_hora_inicio());
        turno.setFecha_hora_fin(turnoNew.getFecha_hora_fin());
        turno.setEstado(turnoNew.getEstado());

        turnoTrabajoServicio.IngresarTurno(turno);
        return ResponseEntity.ok(turno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> EliminarTurnoId(@PathVariable Integer id) {
        Turno_trabajo turno = turnoTrabajoServicio.MostrarTurnoID(id);
        if (turno == null) {
            throw new ExcepcionRecursoNoEncontrado("No se encontró el Id del turno de trabajo: " + id);
        }
        turnoTrabajoServicio.EliminarTurno(turno);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
