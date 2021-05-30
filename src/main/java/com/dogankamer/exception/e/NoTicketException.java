package com.dogankamer.exception.e;

import org.springframework.http.HttpStatus;


public class NoTicketException extends RuntimeException {

    public static final int CODE = -1;

    public static final HttpStatus HTTP_STATUS = HttpStatus.NOT_FOUND;

    public NoTicketException(String message) {
        super(message);
    }
}
