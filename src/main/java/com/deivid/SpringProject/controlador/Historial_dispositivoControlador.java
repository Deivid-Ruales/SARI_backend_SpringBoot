package com.deivid.SpringProject.controlador;

import com.deivid.SpringProject.modelo.Historial_dispositivo;
import com.deivid.Excepcion.ExcepcionRecursoNoEncontrado;
import com.deivid.SpringProject.modelo.Dispositivo;
import com.deivid.SpringProject.modelo.Turno_trabajo;
import com.deivid.SpringProject.servicio.IHistorial_dispositivoServicio;
import com.deivid.SpringProject.servicio.Historial_dispositivoServicio;
import com.deivid.SpringProject.servicio.IDispositivoServicio;
import com.deivid.SpringProject.servicio.ITurno_trabajoServicio;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sari/historiales")
@CrossOrigin(value = "http://localhost:3000/")
public class Historial_dispositivoControlador {

    private static final Logger logger = LoggerFactory.getLogger(Historial_dispositivoControlador.class);

    @Autowired
    private IHistorial_dispositivoServicio historialDispositivoServicio;
    
    @Autowired
    private IDispositivoServicio dispositivoServicio;
    
    @Autowired
    private ITurno_trabajoServicio turnoServicio;
    
    @GetMapping
    public List<Historial_dispositivo> MostrarHistoriales() {
        var historiales = historialDispositivoServicio.MostrarTodosHistoriales();
        
        // Filtrar por el estado activo
        List<Historial_dispositivo> historialesFiltrados = historiales.stream()
                .filter(historial -> historial.getActive().equals(true))
                .collect(Collectors.toList());

        
        historiales.forEach(historial -> logger.info(historial.toString()));
        return historialesFiltrados;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Historial_dispositivo> MostrarHistorialId(@PathVariable Integer id) {
        Historial_dispositivo historial = historialDispositivoServicio.MostrarHistorialID(id);
        if (historial == null) {
            throw new ExcepcionRecursoNoEncontrado("No se encontró el Id del historial de dispositivo: " + id);
        }
        
         if (historial.getActive() == true) {
            return ResponseEntity.ok(historial);
        } else {
            return (ResponseEntity<Historial_dispositivo>) ResponseEntity.notFound();
        }
    }

    @PostMapping
    public void IngresarHistorial(@RequestBody Historial_dispositivo historial) {
        logger.info("Historial a ingresar: " + historial);
        
        historial.setActive(true);
        
        historialDispositivoServicio.IngresarHistorial(historial);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Historial_dispositivo> ModificarHistorialId(@PathVariable Integer id, @RequestBody Historial_dispositivo historialNew) {
        Historial_dispositivo historial = historialDispositivoServicio.MostrarHistorialID(id);
        if (historial == null) {
            throw new ExcepcionRecursoNoEncontrado("No se encontró el Id del historial de dispositivo: " + id);
        }

        // Actualizar otros campos
        historial.setAlmohadillas(historialNew.getAlmohadillas());
        historial.setPaginas_impresas(historialNew.getPaginas_impresas());
        historial.setPaginas_adf(historialNew.getPaginas_adf());
        historial.setObservaciones(historialNew.getObservaciones());
        historial.setDispositivo(historialNew.getDispositivo());
        historial.setTurno(historialNew.getTurno());

        historialDispositivoServicio.IngresarHistorial(historial);
        return ResponseEntity.ok(historial);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> EliminarHistorialId(@PathVariable Integer id) {
        Historial_dispositivo historial = historialDispositivoServicio.MostrarHistorialID(id);
        if (historial == null) {
            throw new ExcepcionRecursoNoEncontrado("No se encontró el Id del historial de dispositivo: " + id);
        }
        
        historial.setActive(false);
        
        historialDispositivoServicio.IngresarHistorial(historial);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
    
    @GetMapping("/dispositivo/{id}")
    public List<Historial_dispositivo> MostrarHistorialesIdDispositivo (@PathVariable Integer id) {
        // Obtener dispositivo por id
        var dispositivo = dispositivoServicio.MostrarDispositivoID(id);
                
        // Obtener todos los historiales
        var historiales = historialDispositivoServicio.MostrarTodosHistoriales();
        
        // Filtrar dispositivos por el id del usuario asociado
        List<Historial_dispositivo> historialesFiltrados = historiales.stream()
                .filter(historial -> historial.getDispositivo() .equals(dispositivo))
                .filter(historial -> historial.getActive().equals(true))
                .collect(Collectors.toList());

        return historialesFiltrados;
    }
    
    @PostMapping("/{id}")
    public void IngresarHistorialIdDispositivo (@PathVariable Integer id, @RequestBody Historial_dispositivo historial){
        Dispositivo dispositivo = dispositivoServicio.MostrarDispositivoID(id);
        Turno_trabajo turno =  turnoServicio.MostrarTurnoID(1);
        
        // Ingresar otros campos por defecto
        historial.setDispositivo(dispositivo);
        historial.setTurno(turno);
        historial.setActive(true);

        historialDispositivoServicio.IngresarHistorial(historial);
    }
}
