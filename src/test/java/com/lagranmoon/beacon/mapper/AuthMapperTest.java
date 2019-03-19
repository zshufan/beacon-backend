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
        userAuth = new UserAuth();
        userAuth.setUserName("Lagranmoon");
        userAuth.setOpenId("20190319");
        userAuth.setSessionKey("fgeuhysdyfgwq9eiuq9eduwqhd");
    }

    @Test
    @Transactional
    public void testSaveUser(){
        authMapper.saveUser(userAuth);
        assertThat(userAuth.getUid(),notNullValue());

        String sessionKey = authMapper.getSessionKey(userAuth.getUid());
        assertThat(sessionKey,equalTo(userAuth.getSessionKey()));

        String newSessionKey = "frewhutuiwrehrqqwewq";
        authMapper.updateSessionKey(userAuth.getOpenId(),newSessionKey);
        String newSessionKeySaved = authMapper.getSessionKey(userAuth.getUid());
        assertThat(newSessionKey,equalTo(newSessionKeySaved));
    }

}