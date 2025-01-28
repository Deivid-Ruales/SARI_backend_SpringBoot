package com.deivid.SpringProject.JWT;

import com.deivid.SpringProject.repositorio.IUsuarioRepositorio;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final IUsuarioRepositorio usuarioRepository;

    // Constructor con inyección de dependencias
    public CustomUserDetailsService(IUsuarioRepositorio usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String cedula) throws UsernameNotFoundException {
        return usuarioRepository.findByCedula(cedula)
                .map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con cédula: " + cedula));
    }
}


