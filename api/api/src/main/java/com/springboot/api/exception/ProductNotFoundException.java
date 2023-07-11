package com.springboot.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException{

    private final static long serialVersionUID = 1L;

    public ProductNotFoundException(String msg) {
        super(msg);
    }
}
