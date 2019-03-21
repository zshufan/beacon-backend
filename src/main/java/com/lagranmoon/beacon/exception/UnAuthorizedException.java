package com.lagranmoon.beacon.exception;

import lombok.Getter;

/**
 * @author Lagranmoon
 * 微信授权失败抛出的异常
 */
public class UnAuthorizedException extends RuntimeException {

    @Getter
    private Integer errorCode;

    public UnAuthorizedException(String message, Integer errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public UnAuthorizedException(String message) {
        super(message);
    }
}
