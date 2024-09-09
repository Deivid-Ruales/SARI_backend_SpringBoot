package com.deivid.SpringProject.servicio;

import com.deivid.SpringProject.modelo.Usuario;
import java.util.List;

//Interfaz para usar con la tabla de Usuarios
public interface IUsuarioServicio  {
    
    public List<Usuario> MostrarTodosUsuarios();
    
    public Usuario MostrarUsuarioID(Integer idUsuario);
    
    public void IngresarUsuario(Usuario usuario);
    
    //public void ModificarUsuario(Usuario usuario);

    public void EliminarUsuario(Usuario usuario);
    
}
