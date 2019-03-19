package com.lagranmoon.beacon.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lagranmoon.beacon.model.AuthResponseDto;
import com.lagranmoon.beacon.model.ResponseDto;
import com.lagranmoon.beacon.model.WechatAuthResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;

/**
 * @author Lagranmoon
 */
@RestController
@RequestMapping("/auth")
@PropertySource(value = "classpath:config/wechat.properties")
public class AuthController {
    @Value("${app_id}")
    private String appId;

    @Value("${auth_url}")
    private String authUrl;

    @Value("${app_secret}")
    private String appSecret;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping
    public ResponseEntity auth(@RequestParam("code") String code, HttpServletRequest request) {

        Enumeration<String> headers = request.getHeaderNames();

        if (StringUtils.isEmpty(code)) {
            throw new IllegalArgumentException("code不能为空");
        }

        try {
            WechatAuthResp resp = doAuth(code);

            return ResponseEntity.ok(ResponseDto.builder()
                    .data("2333")
                    .build());

        } catch (Exception e) {
            e.printStackTrace();
            e.getLocalizedMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseDto.builder()
                            .errCode(0x01)
                            .msg("连接微信服务器异常")
                            .build());
        }

    }


    private WechatAuthResp doAuth(String code) throws Exception {

        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter
                = new MappingJackson2HttpMessageConverter(objectMapper);
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(List.of(MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN));
        RestTemplate template = new RestTemplate(List.of(mappingJackson2HttpMessageConverter));

        ResponseEntity<WechatAuthResp> resp =
                template.getForEntity(authUrl, WechatAuthResp.class, appId, appSecret, code);

        System.out.println(resp.getBody());

        return null;
    }


}
