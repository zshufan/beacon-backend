package com.lagranmoon.beacon.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lagranmoon.beacon.exception.UnAuthorizedException;
import com.lagranmoon.beacon.model.ResponseDto;
import com.lagranmoon.beacon.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Lagranmoon
 */
@Slf4j
@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private AuthService authService;

    @Resource
    private ObjectMapper objectMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        try {
            String token = request.getHeader("Authorization");

            if (StringUtils.isEmpty(token)) {
                throw new UnAuthorizedException("Token is missing");
            }

            String openId = authService.verify(token);

            if (openId.isEmpty()) {
                throw new UnAuthorizedException("Invalid Token");
            }

            request.setAttribute("openId", openId);
            return true;
        } catch (UnAuthorizedException e) {
            ResponseDto responseDto = ResponseDto
                    .builder()
                    .errCode(e.getErrorCode())
                    .msg(e.getLocalizedMessage())
                    .build();
            response.getWriter().write(objectMapper.writeValueAsString(responseDto));
            return false;
        }

    }

}
