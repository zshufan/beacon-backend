package com.lagranmoon.beacon.service.impl;

import com.lagranmoon.beacon.model.AuthDto;
import com.lagranmoon.beacon.model.WechatAuthResp;
import com.lagranmoon.beacon.service.WechatService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author Lagranmoon
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AuthServiceImplTest {

    @InjectMocks
    private AuthServiceImpl authService = new AuthServiceImpl();

    @Mock
    private WechatService wechatService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        when(wechatService.code2Session(any(String.class))).thenReturn(
                WechatAuthResp.builder()
                .openId("hsdjfhsfhiw")
                .sessionKey("reiunjsdbnsffw")
                .build()
        );
    }

    @Test
    @Transactional
    public void testAuth(){
        String userName = "vbvidefw";
    }

}