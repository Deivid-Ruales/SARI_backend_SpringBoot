package com.deivid.Excepcion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ExcepcionRecursoNoEncontrado extends RuntimeException{

    public ExcepcionRecursoNoEncontrado(String message) {
        super(message);
    }

}