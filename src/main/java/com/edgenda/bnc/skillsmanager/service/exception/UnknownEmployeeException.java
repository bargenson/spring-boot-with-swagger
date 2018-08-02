package com.edgenda.bnc.skillsmanager.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UnknownEmployeeException extends RuntimeException {

    public UnknownEmployeeException(Long id) {
        super("Unknown Employee with ID=" + id);
    }

}
