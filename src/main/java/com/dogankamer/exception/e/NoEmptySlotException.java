package com.dogankamer.exception.e;

import org.springframework.http.HttpStatus;


public class NoEmptySlotException extends RuntimeException {

    public static final int CODE = -1;

    public static final HttpStatus HTTP_STATUS = HttpStatus.INSUFFICIENT_STORAGE;

    public NoEmptySlotException(String message) {
        super(message);
    }
}
