package com.lagranmoon.beacon.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author Lagranmoon
 */
@Data
public class AuthRequestDto {

    @NotEmpty(message = "request code 不能为空")
    private String code;

    @NotEmpty(message = "用户名不能为空")
    private String nickName;


}
