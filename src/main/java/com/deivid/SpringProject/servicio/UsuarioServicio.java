package com.deivid.SpringProject.servicio;

import com.deivid.SpringProject.modelo.Rol;
import com.deivid.SpringProject.modelo.Usuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.deivid.SpringProject.repositorio.IUsuarioRepositorio;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UsuarioServicio implements IUsuarioServicio {

    @Autowired
    IUsuarioRepositorio usuarioRepositorio;

    @Autowired
    private IUsuarioRepositorio usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

    public void IngresarUsuario(Usuario usuario) {
        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
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
    
    public Usuario Autenticar(String cedula, String contrasena) {
        Usuario usuario = usuarioRepository.findByCedula(cedula)
                .orElseThrow(() -> new UsernameNotFoundException("Cédula no encontrada"));
        
        System.out.println("*********************Cedula: "+cedula+" Contraseña: "+ contrasena);

        if (!passwordEncoder.matches(contrasena, usuario.getContrasena())) {
            throw new BadCredentialsException("Contraseña incorrecta");
        }
                  
        return usuario;
    }
}
