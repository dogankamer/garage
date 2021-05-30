package com.dogankamer.exception;

import lombok.Builder;
import lombok.Data;

/**
 * Holder for invalid fields.
 */
@Data
@Builder
public class FieldValidationReason {

    private final String field;

    private final String message;

    @Override
    public String toString() {
        return String.format("field='%s', message='%s'.", field, message);
    }
}
