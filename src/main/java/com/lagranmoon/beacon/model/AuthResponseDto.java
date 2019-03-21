package com.lagranmoon.beacon.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Lagranmoon
 */
@Data
@AllArgsConstructor
public class AuthResponseDto {

    private Long uid;

    private String token;
}
