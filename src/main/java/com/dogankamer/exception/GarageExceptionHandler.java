package com.dogankamer.exception;

import com.dogankamer.exception.common.ErrorResponse;
import com.dogankamer.exception.e.BadRequestException;
import com.dogankamer.exception.e.NoEmptySlotException;
import com.dogankamer.exception.e.NoTicketException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

import java.time.Instant;

@RestControllerAdvice(basePackages = "com.dogankamer")
public class GarageExceptionHandler {

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public Mono<ErrorResponse> badRequest(BadRequestException exception) {
        ErrorResponse response = generateErrorResponse(BadRequestException.HTTP_STATUS,
                exception.getMessage(), BadRequestException.CODE);
        return Mono.just(response);
    }


    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoTicketException.class)
    public Mono<ErrorResponse> badRequest(NoTicketException exception) {
        ErrorResponse response = generateErrorResponse(NoTicketException.HTTP_STATUS,
                exception.getMessage(), NoTicketException.CODE);
        return Mono.just(response);
    }

    @ResponseStatus(value = HttpStatus.INSUFFICIENT_STORAGE)
    @ExceptionHandler(NoEmptySlotException.class)
    public Mono<ErrorResponse> badRequest(NoEmptySlotException exception) {
        ErrorResponse response = generateErrorResponse(NoEmptySlotException.HTTP_STATUS,
                exception.getMessage(), NoEmptySlotException.CODE);
        return Mono.just(response);
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public Mono<ErrorResponse> unCaughtException(Throwable throwable) {
        throwable.printStackTrace();
        ErrorResponse response = generateErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                throwable.getMessage(), -1);
        return Mono.just(response);
    }


    private ErrorResponse generateErrorResponse(HttpStatus status,
                                                String errorDescription,
                                                int errorCode) {
        Instant now = Instant.now();
        return ErrorResponse.builder().
                timestamp(now.getEpochSecond()).
                error(status.getReasonPhrase()).
                errorDescription(errorDescription).
                code(errorCode).
                status(status.name()).
                statusCode(status.value()).
                build();
    }

}
