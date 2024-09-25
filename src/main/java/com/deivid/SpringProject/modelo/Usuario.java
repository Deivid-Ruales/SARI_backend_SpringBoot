package com.deivid.SpringProject.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//Se ponen los decoradores adecuados para la clase
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Usuario {
    
    //Se decora la variable como id para generar el valor automaticamente
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_usuario;

    // Define otras columnas en la tabla
    private String nombre;
    private String cedula;
    private String email;
    private String direccion;
    private String telefono;
    private String contrasena;
    private Boolean active;
    
    // Define una columna que almacena el rol del usuario usando el enum Rol
    @Enumerated(EnumType.STRING)
    private Rol rol;
}
