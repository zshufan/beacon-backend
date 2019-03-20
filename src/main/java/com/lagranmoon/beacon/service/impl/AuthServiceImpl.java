package com.lagranmoon.beacon.service.impl;

import com.lagranmoon.beacon.mapper.AuthMapper;
import com.lagranmoon.beacon.model.AuthResponseDto;
import com.lagranmoon.beacon.service.AuthService;
import com.lagranmoon.beacon.util.JwtKeyResolver;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Lagranmoon
 */
@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    private AuthMapper authMapper;

    @Resource
    private JwtKeyResolver resolver;

    @Override
    public AuthResponseDto auth(String code) {
        return null;
    }

    @Override
    public String verify(String token) {
        try {
            String kid = Jwts.parser()
                    .setSigningKeyResolver(resolver)
                    .parseClaimsJws(token)
                    .getHeader()
                    .getKeyId();

            return authMapper.getOpenIdByid(Long.valueOf(kid));
        }catch (Exception e){
            log.info("{} is invalid",token);
            return "";
        }
    }
}
