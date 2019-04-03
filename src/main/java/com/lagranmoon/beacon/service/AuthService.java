package com.lagranmoon.beacon.service;

import com.lagranmoon.beacon.model.AuthDto;

/**
 * @author Lagranmoon
 * Token过期时间3天
 */
public interface AuthService {

    /**
     * 根据request code从微信服务器获取openID
     * @param code request code
     * @param userName 用户名
     * @return 返回自定义登录态（JWT Token）和UID
     */
    AuthDto auth(String code, String userName);

    /**
     * 判断Token是否为有效的Token
     * @param token 需要验证的Token
     * @return 返回用户的uid
     */
    String  verify(String token);
    

}
