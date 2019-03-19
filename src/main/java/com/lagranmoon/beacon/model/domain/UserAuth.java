package com.lagranmoon.beacon.model.domain;

import lombok.Data;

/**
 * @author Lagranmoon
 */
@Data
public class UserAuth {

    private Long uid;

    private String openId;

    private String userName;

    private String sessionKey;
}
