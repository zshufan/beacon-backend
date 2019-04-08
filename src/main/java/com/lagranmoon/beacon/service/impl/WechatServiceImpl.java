package com.lagranmoon.beacon.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lagranmoon.beacon.exception.UnAuthorizedException;
import com.lagranmoon.beacon.model.WechatAuthResp;
import com.lagranmoon.beacon.service.WechatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

/**
 * @author Lagranmoon
 */
@Slf4j
@Service
@PropertySource(value = "classpath:config/wechat.properties")
public class WechatServiceImpl implements WechatService {

    @Value("${app_id}")
    private String appId;

    @Value("${auth_url}")
    private String authUrl;

    @Value("${app_secret}")
    private String appSecret;

    @Resource
    private ObjectMapper objectMapper;

    @Override
    public WechatAuthResp code2Session(String code) {

        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter
                = new MappingJackson2HttpMessageConverter(objectMapper);
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN));
        RestTemplate template = new RestTemplate(Collections.singletonList(mappingJackson2HttpMessageConverter));

        WechatAuthResp wechatResp;
        try {
            wechatResp =
                    template.getForObject(authUrl, WechatAuthResp.class, appId, appSecret, code);
            Objects.requireNonNull(wechatResp);
        } catch (Exception e) {
            log.debug(e.getLocalizedMessage());
            throw new UnAuthorizedException("连接微信服务器失败");
        }

        return wechatResp;

    }
}
