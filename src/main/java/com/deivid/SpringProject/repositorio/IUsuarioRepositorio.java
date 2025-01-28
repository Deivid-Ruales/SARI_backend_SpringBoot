package com.deivid.SpringProject.repositorio;

import com.deivid.SpringProject.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsuarioRepositorio extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByCedula(String cedula);
}
