package com.lagranmoon.beacon.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lagranmoon.beacon.exception.UnAuthorizedException;
import com.lagranmoon.beacon.mapper.AuthMapper;
import com.lagranmoon.beacon.model.AuthResponseDto;
import com.lagranmoon.beacon.model.WechatAuthResp;
import com.lagranmoon.beacon.model.domain.UserAuth;
import com.lagranmoon.beacon.service.AuthService;
import com.lagranmoon.beacon.util.JwtKeyResolver;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.security.Key;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author Lagranmoon
 */
@Slf4j
@Service
@PropertySource(value = "classpath:config/wechat.properties")
public class AuthServiceImpl implements AuthService {


    @Value("${app_id}")
    private String appId;

    @Value("${auth_url}")
    private String authUrl;

    @Value("${app_secret}")
    private String appSecret;

    @Resource
    private AuthMapper authMapper;

//    @Resource
//    private JwtKeyResolver resolver;

    private RedisTemplate redisTemplate;

    @Resource
    private ObjectMapper objectMapper;

    @Override
    @SuppressWarnings("unchecked")
    public AuthResponseDto auth(String code, String userName) {

        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter
                = new MappingJackson2HttpMessageConverter(objectMapper);
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(List.of(MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN));
        RestTemplate template = new RestTemplate(List.of(mappingJackson2HttpMessageConverter));


        WechatAuthResp wechatResp;
        try {
            wechatResp =
                    template.getForObject(authUrl, WechatAuthResp.class, appId, appSecret, code);
            Objects.requireNonNull(wechatResp);
        } catch (Exception e) {
            log.debug(e.getLocalizedMessage());
            throw new UnAuthorizedException("连接微信服务器失败");
        }


        if (StringUtils.isEmpty(wechatResp.getSessionKey())) {
            throw new UnAuthorizedException(wechatResp.getErrMsg(), wechatResp.getErrCode());
        }

        UserAuth userAuth = UserAuth
                .builder()
                .userName(userName)
                .openId(wechatResp.getOpenId())
                .sessionKey(wechatResp.getSessionKey())
                .build();

        authMapper.saveUser(userAuth);

        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        String token = Jwts.builder()
//                .setHeaderParam("kid", userAuth.getId())
                .signWith(key)
                .setSubject(String.valueOf(userAuth.getId()))
                .setExpiration(new Date(System.currentTimeMillis()+3*24*3600))
                .compact();

        redisTemplate.opsForValue().set(token,
                Base64Utils.encodeToString(key.getEncoded()), Duration.ofDays(3));

        return new AuthResponseDto(userAuth.getId(), token);


    }

    @Override
    public String verify(String token) {
        try {

            String key = (String) redisTemplate.opsForValue().get(token);
            Objects.requireNonNull(key);


            String uid = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();

            return authMapper.getOpenIdById(Long.valueOf(uid));
        } catch (Exception e) {
            log.info("{} is invalid", token);
            return "";
        }
    }

}
