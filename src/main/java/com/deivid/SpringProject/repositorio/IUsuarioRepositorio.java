package com.deivid.SpringProject.repositorio;

import com.deivid.SpringProject.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepositorio extends JpaRepository<Usuario, Integer>{
    
}
