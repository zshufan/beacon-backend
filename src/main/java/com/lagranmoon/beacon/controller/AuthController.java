package com.lagranmoon.beacon.controller;

import com.lagranmoon.beacon.model.AuthDto;
import com.lagranmoon.beacon.model.AuthRequestDto;
import com.lagranmoon.beacon.model.ResponseDto;
import com.lagranmoon.beacon.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author Lagranmoon
 */
@RestController
@RequestMapping("/auth")
public class AuthController {


    @Resource
    private AuthService authService;

    @PostMapping
    public ResponseEntity auth(@RequestBody @Valid AuthRequestDto requestDto) {

        AuthDto response = authService.auth(requestDto);
        return ResponseEntity
                .ok(ResponseDto.builder()
                        .data(response)
                        .build());
    }


}
