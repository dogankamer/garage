package com.dogankamer.exception.e;

import org.springframework.http.HttpStatus;


public class BadRequestException extends RuntimeException {

    public static final int CODE = -1;

    public static final HttpStatus HTTP_STATUS = HttpStatus.BAD_REQUEST;

    public BadRequestException(String message) {
        super(message);
    }
}
