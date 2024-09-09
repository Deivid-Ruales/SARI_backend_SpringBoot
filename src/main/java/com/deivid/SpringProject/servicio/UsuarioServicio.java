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
    /*    Usuario nuevoUsuario = new Usuario();
        Rol rol;
        nuevoUsuario.setNombre("David");
        nuevoUsuario.setCedula("10050005001");
        nuevoUsuario.setEmail("david@gmail.com");
        nuevoUsuario.setDireccion("Calle 11 No. 2 - 15");
        nuevoUsuario.setTelefono("3153335555");
        nuevoUsuario.setRol(Rol.Cliente);
        nuevoUsuario.setContrasena("123456");
    */
        usuarioRepositorio.save(usuario);
    }

    @Override
    public void EliminarUsuario(Usuario usuario) {
        usuarioRepositorio.delete(usuario);
    }
}
