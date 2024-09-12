package com.deivid.SpringProject.servicio;

import com.deivid.SpringProject.modelo.Rol;
import com.deivid.SpringProject.modelo.Usuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.deivid.SpringProject.repositorio.IUsuarioRepositorio;

@Service
public class UsuarioServicio implements IUsuarioServicio{
    
    @Autowired IUsuarioRepositorio usuarioRepositorio;
    
    //Método para mostrar todos los usuarios de la base de datos
    @Override
    public List<Usuario> MostrarTodosUsuarios() {
        return usuarioRepositorio.findAll();
    }

    @Override
    public Usuario MostrarUsuarioID(Integer idUsuario) {
        Usuario usuario = usuarioRepositorio.findById(idUsuario).orElse(null);
        return usuario;
    }
    
    public void IngresarUsuario(Usuario usuario){
        usuarioRepositorio.save(usuario);
    }

    @Override
    public void EliminarUsuario(Usuario usuario) {
        usuarioRepositorio.delete(usuario);
    }

    @Override
    public List<Usuario> MostrarSoloEmpleados() {
        return usuarioRepositorio.findAll();
    }
}
