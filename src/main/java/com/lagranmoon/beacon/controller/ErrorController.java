package com.lagranmoon.beacon.controller;

import com.lagranmoon.beacon.exception.UnAuthorizedException;
import com.lagranmoon.beacon.model.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.MethodNotAllowedException;

import java.util.List;
import java.util.Objects;

/**
 * @author Lagranmoon
 */
@Slf4j
@RestController
@ControllerAdvice
public class ErrorController  {


    @ExceptionHandler(UnAuthorizedException.class)
    public ResponseEntity<ResponseDto> handle401(UnAuthorizedException e) {

        log.debug("errorCode:{}", e.getErrorCode());

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(
                        ResponseDto
                                .builder()
                                .errCode(e.getErrorCode())
                                .msg(e.getLocalizedMessage())
                                .build()
                );
    }

    @ExceptionHandler(MethodNotAllowedException.class)
    public ResponseEntity<ResponseDto> handle405(MethodNotAllowedException e) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(
                        ResponseDto.builder()
                                .msg(e.getHttpMethod() + " is not support")
                                .build()
                );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto> handleArgumentNotValid(MethodArgumentNotValidException e) {

        FieldError error = e.getBindingResult().getFieldError();
        String errMsg = "";
        if (Objects.nonNull(error)) {
            errMsg = error.getDefaultMessage();
        }

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ResponseDto
                                .builder()
                                .msg(errMsg)
                                .build()

                );


    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto> handleError(Exception e){

        log.error("Server Internal Error",e);

        if (e instanceof UnAuthorizedException){
            return handle401((UnAuthorizedException) e);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        ResponseDto.builder()
                        .msg(e.getLocalizedMessage())
                        .build()
                );
    }


}
