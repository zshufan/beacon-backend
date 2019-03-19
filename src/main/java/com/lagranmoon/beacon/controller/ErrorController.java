package com.lagranmoon.beacon.controller;

import com.lagranmoon.beacon.model.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lagranmoon
 */
@RestController
@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseDto> handleInvalidArgument(IllegalArgumentException e) {
        return ResponseEntity.badRequest()
                .body(
                        ResponseDto.builder()
                                .errCode(0x01)
                                .msg(e.getMessage())
                                .build()
                );
    }

}
