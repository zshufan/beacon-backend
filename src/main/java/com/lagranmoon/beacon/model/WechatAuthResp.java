package com.lagranmoon.beacon.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @author Lagranmoon
 */
@Data
@Builder
public class WechatAuthResp {

    @JsonProperty("openid")
    private String openId;

    private String sessionKey;

    @JsonProperty("unionid")
    private String unionId;

    @JsonProperty("errcode")
    private Integer errCode;

    @JsonProperty("errmsg")
    private String errMsg;


}
