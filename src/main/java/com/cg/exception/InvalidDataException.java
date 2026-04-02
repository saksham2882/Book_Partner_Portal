package com.cg.exception;

import lombok.Getter;

@Getter
public class InvalidDataException extends RuntimeException {

    private String errorCode;

    public InvalidDataException(String message) {
        super(message);
    }

    public InvalidDataException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

}