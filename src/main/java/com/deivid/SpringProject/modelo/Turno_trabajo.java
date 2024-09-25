package com.deivid.SpringProject.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
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
public class Turno_trabajo {

    // Se decora la variable como id para generar el valor automáticamente
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_turno;

    // Se define la columna para la fecha y hora de inicio
    @NotNull(message = "La fecha y hora de inicio no pueden ser nulas")
    private LocalDateTime fecha_hora_inicio;

    // Se define la columna para la fecha y hora de fin
    private LocalDateTime fecha_hora_fin;
    
    private Boolean active;

    // Se define el estado usando el enum Estado
    @Enumerated(EnumType.STRING)
    @NotNull(message = "El estado del turno no puede ser nulo")
    private Estado estado;

    // Se define la relación Many-to-One con Dispositivo
    @ManyToOne
    @JoinColumn(name = "id_dispositivo")
    @NotNull(message = "El dispositivo no puede ser nulo")
    private Dispositivo dispositivo;

    // Se define la relación Many-to-One con Usuario
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    @NotNull(message = "El usuario no puede ser nulo")
    private Usuario usuario;
}
