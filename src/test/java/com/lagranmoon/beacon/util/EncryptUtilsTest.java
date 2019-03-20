package com.lagranmoon.beacon.util;

import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;


/**
 * @author Lagranmoon
 */
public class EncryptUtilsTest {

    @Test
    public void testEncryptSha256(){
        assertThat(EncryptUtils.encryptSha256(null),isEmptyString());
        assertThat(EncryptUtils.encryptSha256(""),not(isEmptyString()));
        assertThat(EncryptUtils.encryptSha256("2333"),not(isEmptyString()));
    }

    @Test
    public void testMatches(){
        assertThat(EncryptUtils.matches(null,null),is(false));
        assertThat(EncryptUtils.matches(null,"2333"),is(false));
        assertThat(EncryptUtils.matches("2333",null),is(false));
        assertThat(EncryptUtils.matches("2333",EncryptUtils.encryptSha256("2333")),is(true));

    }


}