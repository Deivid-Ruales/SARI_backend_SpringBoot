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
public class Historial_dispositivo {

    // Se decora la variable como id para generar el valor automáticamente
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_historial;

    // Define una relación muchos-a-uno con la entidad Dispositivo
    @ManyToOne
    @JoinColumn(name = "id_dispositivo")
    private Dispositivo dispositivo;

    // Define una relación muchos-a-uno con la entidad Turno_trabajo
    @ManyToOne
    @JoinColumn(name = "id_turno")
    private Turno_trabajo turno;

    // Define las columnas adicionales en la tabla
    private String almohadillas;
    private String paginas_impresas;
    private String paginas_adf;
    private String observaciones;
}
