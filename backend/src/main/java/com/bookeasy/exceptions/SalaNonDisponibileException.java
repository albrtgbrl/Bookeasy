package com.bookeasy.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class SalaNonDisponibileException extends Exception {

    public SalaNonDisponibileException() {
        super();
    }

    public SalaNonDisponibileException(String errorMessage) {
        super(errorMessage);
    }

    public SalaNonDisponibileException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

}
