package com.dogankamer.exception.common;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorResponse {

    private final int statusCode;

    private final String status;

    private final long timestamp;

    private final String date;

    private final int code;

    private final String error;

    private final String errorDescription;
}
