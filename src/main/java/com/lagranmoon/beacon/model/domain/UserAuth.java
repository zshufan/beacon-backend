package com.lagranmoon.beacon.model.domain;

import lombok.Builder;
import lombok.Data;

/**
 * @author Lagranmoon
 */
@Data
@Builder
public class UserAuth {

    private Long id;

    private String openId;

    private String userName;

    private String sessionKey;
}
