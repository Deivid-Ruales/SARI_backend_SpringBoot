package com.deivid.SpringProject.controlador;

import com.deivid.SpringProject.modelo.Usuario;
import com.deivid.SpringProject.servicio.IUsuarioServicio;
import com.deivid.SpringProject.servicio.UsuarioServicio;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sari")
@CrossOrigin(value = "http://localhost:3000/")
public class UsuarioControlador {
    
    private static final Logger logger = 
            LoggerFactory.getLogger(UsuarioControlador.class);
    
    @Autowired 
    private IUsuarioServicio usuarioServicio;
    
    @GetMapping("/usuarios")
    public List<Usuario> MostrarTodosUsuario (){
        var usuarios = usuarioServicio.MostrarTodosUsuarios();
        usuarios.forEach(usuario -> logger.info(usuario.toString()));
        return usuarios;
    }
    
}
