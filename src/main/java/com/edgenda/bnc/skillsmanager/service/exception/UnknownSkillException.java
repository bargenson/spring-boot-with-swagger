package com.edgenda.bnc.skillsmanager.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UnknownSkillException extends RuntimeException {

    public UnknownSkillException(Long id) {
        super("Unknown Skill with ID=" + id);
    }

}
