package com.deivid.SpringProject.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

// Se colocan los decoradores adecuados para la clase
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Dispositivo {

    // Se decora la variable como id para generar el valor automáticamente
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_dispositivo;

    // Define otras columnas en la tabla
    private String tipo;
    private String marca;
    private String referencia;
    private String serial_num;
    private String descripcion;
    private Boolean active;

    // Define una relación muchos-a-uno con la entidad Usuario y la columna 'id_usuario'
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}
