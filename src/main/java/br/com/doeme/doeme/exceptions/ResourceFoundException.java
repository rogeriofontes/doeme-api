package br.com.doeme.doeme.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Recurso já existente")
public class ResourceFoundException extends Exception {

    public ResourceFoundException(String message) {
        super(message);
    }
}
