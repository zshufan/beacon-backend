package com.lagranmoon.beacon.controller;

import com.lagranmoon.beacon.model.AuthDto;
import com.lagranmoon.beacon.model.AuthRequestDto;
import com.lagranmoon.beacon.model.ResponseDto;
import com.lagranmoon.beacon.service.AuthService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value = "身份认证",notes = "根据request code向微信服务器请求并将用户信息存入数据库")
    @ApiImplicitParam(name = "requestDto",value = "用户认证实体类",dataType = "AuthRequestDto",required = true)
    public ResponseEntity auth(@RequestBody @Valid AuthRequestDto requestDto) {

//        AuthDto response = authService.auth(requestDto.getCode(), requestDto.getNickName());
        AuthDto response = new AuthDto(2L, "2333");
        return ResponseEntity
                .ok(ResponseDto.builder()
                        .data(response)
                        .msg("2333")
                        .errCode(22323)
                        .build());
    }


}
