package com.reservation.api.exceptions;

import com.reservation.api.response.ValidationErrorResponse;
import lombok.Getter;

import java.util.Collections;

@Getter
public class CustomNotFoundException extends RuntimeException{

    private final ValidationErrorResponse errorResponse;

    public CustomNotFoundException(String field, String message) {
        super(message);
        this.errorResponse = new ValidationErrorResponse(
                Collections.singletonList(new ValidationErrorResponse.ValidationError(field, message))
        );
    }

}
