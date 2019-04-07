package com.lagranmoon.beacon.exception;

import lombok.Getter;

/**
 * @author Lagranmoon
 */
public class ResourceNotFoundException extends RuntimeException{

    @Getter
    private Integer errCode;

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Integer errCode) {
        super(message);
        this.errCode = errCode;
    }
}
