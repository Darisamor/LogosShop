package com.darisamor.logosShop.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    public NotFoundException(String type, String identificator, String value) {
        super(String.format("%s with %s : %s not found.", type, identificator, value));
        log.error("{} not found.", type, this);
    }

    public NotFoundException(String type, String identificator, Number value) {
        new NotFoundException(type, identificator, String.valueOf(value));
    }
}