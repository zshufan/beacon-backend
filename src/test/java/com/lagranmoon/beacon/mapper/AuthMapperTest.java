package com.lagranmoon.beacon.mapper;

import com.lagranmoon.beacon.model.domain.UserAuth;
import org.junit.*;
import org.junit.runner.RunWith;
import javax.annotation.Resource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * @author Lagranmoon
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AuthMapperTest {

    @Resource
    private AuthMapper authMapper;

    private static UserAuth userAuth;

    @BeforeClass
    public static void prepareData(){
        userAuth = UserAuth.builder()
                .userName("Lagranmoon")
                .openId("20190319")
                .sessionKey("fgruhdsfrgbruyfgweufwefw")
                .build();
    }

    @Test
    @Transactional
    public void testSaveUser(){
        authMapper.saveUser(userAuth);
        assertThat(userAuth.getId(),notNullValue());

        String sessionKey = authMapper.getSessionKeyByUid(userAuth.getId());
        assertThat(sessionKey,equalTo(userAuth.getSessionKey()));

        String newSessionKey = "frewhutuiwrehrqqwewq";
        authMapper.updateSessionKey(userAuth.getOpenId(),newSessionKey);
        String newSessionKeySaved = authMapper.getSessionKeyByUid(userAuth.getId());
        assertThat(newSessionKey,equalTo(newSessionKeySaved));
    }

}