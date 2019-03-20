package com.lagranmoon.beacon.interceptor;

import com.lagranmoon.beacon.exception.UnAuthorizedException;
import com.lagranmoon.beacon.service.AuthService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Lagranmoon
 *
 */
@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private AuthService authService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token =request.getHeader("Authorization");

        if (token.isEmpty()){
            throw new UnAuthorizedException("Token is missing");
        }

        String openId = authService.verify(token);

        if (openId.isEmpty()){
            throw new UnAuthorizedException("Invalid Token");
        }

        request.setAttribute("openId",openId);
        return true;
    }
}
