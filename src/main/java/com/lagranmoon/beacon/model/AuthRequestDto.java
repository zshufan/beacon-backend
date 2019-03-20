package com.lagranmoon.beacon.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author Lagranmoon
 */
@Data
public class AuthRequestDto {

    @NotEmpty
    private String code;

    @NotEmpty
    private String nickName;


}
