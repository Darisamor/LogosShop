package com.darisamor.logosShop.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ResponseStatus(HttpStatus.CONFLICT)
public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String type, String identificator, String value) {
        super(String.format("%s with %s : %s is already exists.", type, identificator, value));
        log.error("{} already exists.", type, this);
    }

    public AlreadyExistsException(String type, String identificator, Number value) {
        new NotFoundException(type, identificator, String.valueOf(value));
    }
}
