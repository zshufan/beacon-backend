package com.lagranmoon.beacon.util;

import com.lagranmoon.beacon.mapper.AuthMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.SigningKeyResolverAdapter;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.security.Key;

/**
 * @author Lagranmoon
 */
@Slf4j
@Component
public class JwtKeyResolver extends SigningKeyResolverAdapter {


    @Resource
    private AuthMapper authMapper;

    @Override
    public Key resolveSigningKey(JwsHeader header, Claims claims) {

        try {
            Long kid = Long.valueOf(header.getKeyId());
            String sessionKey = authMapper.getSessionKeyByUid(kid);
            return Keys.hmacShaKeyFor(sessionKey.getBytes());
        }catch (Exception e){
            log.debug("{} is invalid",header.getKeyId());
            return null;
        }

    }
}
