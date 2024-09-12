package com.deivid.SpringProject.controlador;

import com.deivid.SpringProject.modelo.Usuario;
import com.deivid.Excepcion.ExcepcionRecursoNoEncontrado;
import com.deivid.SpringProject.modelo.Dispositivo;
import com.deivid.SpringProject.modelo.Rol;
import com.deivid.SpringProject.servicio.IUsuarioServicio;
import com.deivid.SpringProject.servicio.UsuarioServicio;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sari/usuarios")
@CrossOrigin(value = "http://localhost:3000/")
public class UsuarioControlador {
    
    private static final Logger logger = 
            LoggerFactory.getLogger(UsuarioControlador.class);
    
    @Autowired 
    private IUsuarioServicio usuarioServicio;
    
    @GetMapping
    public List<Usuario> MostrarUsuarios (){
        var usuarios = usuarioServicio.MostrarTodosUsuarios();
        usuarios.forEach(usuario -> logger.info(usuario.toString()));
        return usuarios;
    }
    
   @GetMapping("/{id}")
    public ResponseEntity<Usuario> MostrarUsuarioId(@PathVariable Integer id) {
        Usuario usuario = usuarioServicio.MostrarUsuarioID(id);
        if (usuario == null) {
            throw new ExcepcionRecursoNoEncontrado("No se encontró el Id del usuario: " + id);
        }
        return ResponseEntity.ok(usuario);
    }
    
    @PostMapping
    public void IngresarUsuario (@RequestBody Usuario usuario) {
        logger.info("Usuario a ingresar: " + usuario);
        usuarioServicio.IngresarUsuario(usuario);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> ModificarUsuarioId(@PathVariable Integer id, @RequestBody Usuario usuarioNew) {
        Usuario usuario = usuarioServicio.MostrarUsuarioID(id);
        if (usuario == null) {
            throw new ExcepcionRecursoNoEncontrado("No se encontró el Id del usuario: " + id);
        }
        
        usuario.setCedula(usuarioNew.getCedula());
        usuario.setContrasena(usuarioNew.getContrasena());
        usuario.setDireccion(usuarioNew.getDireccion());
        usuario.setEmail(usuarioNew.getEmail());
        usuario.setNombre(usuarioNew.getNombre());
        usuario.setRol(usuarioNew.getRol());
        usuario.setTelefono(usuarioNew.getTelefono());
        
        usuarioServicio.IngresarUsuario(usuario);
        return ResponseEntity.ok(usuario);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> EliminarUsuarioId(@PathVariable Integer id) {
        Usuario usuario = usuarioServicio.MostrarUsuarioID(id);
        if (usuario == null) {
            throw new ExcepcionRecursoNoEncontrado("No se encontró el Id del usuario: " + id);
        }
        usuarioServicio.EliminarUsuario(usuario);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
    
    @GetMapping("/empleados")
    public List<Usuario> MostrarSoloEmpleados() {                
        // Obtener todos los usuarios
        var usuarios = usuarioServicio.MostrarTodosUsuarios();
        
        // Declaro el rol que necesito filtrar del enum
        Rol rol = Rol.Empleado;
        
        // Filtrar usuarios por el rol = empleado
        List<Usuario> usuariosFiltrados = usuarios.stream()
                .filter(usuario -> usuario.getRol() .equals(rol))
                .collect(Collectors.toList());

        return usuariosFiltrados;
    }
}
