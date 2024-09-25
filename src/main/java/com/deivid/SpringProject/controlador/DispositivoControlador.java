package com.deivid.SpringProject.controlador;

import com.deivid.SpringProject.modelo.Dispositivo;
import com.deivid.Excepcion.ExcepcionRecursoNoEncontrado;
import com.deivid.SpringProject.servicio.IDispositivoServicio;
import com.deivid.SpringProject.servicio.DispositivoServicio;
import com.deivid.SpringProject.servicio.IUsuarioServicio;
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
@RequestMapping("sari/dispositivos")
@CrossOrigin(value = "http://localhost:3000/")
public class DispositivoControlador {

    private static final Logger logger = LoggerFactory.getLogger(DispositivoControlador.class);

    @Autowired
    private IDispositivoServicio dispositivoServicio;

    @Autowired
    private IUsuarioServicio usuarioServicio;

    @GetMapping
    public List<Dispositivo> MostrarDispositivos() {
        var dispositivos = dispositivoServicio.MostrarTodosDispositivos();

        // Filtrar dispositivos por el estado activo
        List<Dispositivo> dispositivosFiltrados = dispositivos.stream()
                .filter(dispositivo -> dispositivo.getActive().equals(true))
                .collect(Collectors.toList());

        return dispositivosFiltrados;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dispositivo> MostrarDispositivoId(@PathVariable Integer id) {
        Dispositivo dispositivo = dispositivoServicio.MostrarDispositivoID(id);
        if (dispositivo == null) {
            throw new ExcepcionRecursoNoEncontrado("No se encontró el Id del dispositivo: " + id);
        }

        if (dispositivo.getActive() == true) {
            return ResponseEntity.ok(dispositivo);
        } else {
            return (ResponseEntity<Dispositivo>) ResponseEntity.notFound();
        }
    }

    @PostMapping
    public void IngresarDispositivo(@RequestBody Dispositivo dispositivo) {
        logger.info("Dispositivo a ingresar: " + dispositivo);
        dispositivo.setActive(true);
        
        dispositivoServicio.IngresarDispositivo(dispositivo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dispositivo> ModificarDispositivoId(@PathVariable Integer id, @RequestBody Dispositivo dispositivoNew) {
        Dispositivo dispositivo = dispositivoServicio.MostrarDispositivoID(id);
        if (dispositivo == null) {
            throw new ExcepcionRecursoNoEncontrado("No se encontró el Id del dispositivo: " + id);
        }

        dispositivo.setTipo(dispositivoNew.getTipo());
        dispositivo.setMarca(dispositivoNew.getMarca());
        dispositivo.setReferencia(dispositivoNew.getReferencia());
        dispositivo.setSerial_num(dispositivoNew.getSerial_num());
        dispositivo.setDescripcion(dispositivoNew.getDescripcion());

        dispositivoServicio.IngresarDispositivo(dispositivo);
        return ResponseEntity.ok(dispositivo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> EliminarDispositivoId(@PathVariable Integer id) {
        Dispositivo dispositivo = dispositivoServicio.MostrarDispositivoID(id);
        if (dispositivo == null) {
            throw new ExcepcionRecursoNoEncontrado("No se encontró el Id del dispositivo: " + id);
        }
        
        dispositivo.setActive(false);
        
        dispositivoServicio.IngresarDispositivo(dispositivo);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/cliente/{id}")
    public List<Dispositivo> MostrarDispositivoPorUsuario(@PathVariable Integer id) {
        // Obtener usuario por id
        var usuario = usuarioServicio.MostrarUsuarioID(id);

        // Obtener todos los dispositivos
        var dispositivos = dispositivoServicio.MostrarTodosDispositivos();

        // Filtrar dispositivos por el id del usuario asociado
        List<Dispositivo> dispositivosFiltrados = dispositivos.stream()
                .filter(dispositivo -> dispositivo.getUsuario().equals(usuario))
                .filter(dispositivo -> dispositivo.getActive().equals(true))
                .collect(Collectors.toList());
        
        return dispositivosFiltrados;
    }
}
