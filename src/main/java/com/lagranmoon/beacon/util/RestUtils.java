package com.lagranmoon.beacon.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Lagranmoon
 * 请求时存在莫名其妙的空指针异常
 */

@Component
@Deprecated
public class RestUtils {

    @Autowired
    private ObjectMapper objectMapper;

    private RestTemplate restTemplate;


    private RestUtils() {
        System.out.println(Objects.isNull(objectMapper));


        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter
                = new MappingJackson2HttpMessageConverter(objectMapper);
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(List.of(MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN));
        restTemplate = new RestTemplate(List.of(mappingJackson2HttpMessageConverter));
    }

    public RestTemplate getTemplate(){
        System.out.println(Objects.isNull(objectMapper));

        return restTemplate;
    }
}
