package com.lagranmoon.beacon.exception;

/**
 * @author Lagranmoon
 * 微信授权失败抛出的异常
 */
public class UnAuthorizedException extends RuntimeException {

    public UnAuthorizedException(String message) {
        super(message);
    }
}
