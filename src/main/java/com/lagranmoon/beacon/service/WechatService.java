package com.lagranmoon.beacon.service;

import com.lagranmoon.beacon.model.WechatAuthResp;

/**
 * @author Lagranmoon
 */
public interface WechatService {

    WechatAuthResp code2Session(String code);

}
