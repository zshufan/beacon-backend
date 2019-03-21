package com.lagranmoon.beacon.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

/**
 * @author Lagranmoon
 */
@Slf4j
public class EncryptUtils {

    public static String encryptSha256(String raw) {
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            return Base64Utils.encodeToUrlSafeString(sha256.digest(raw.getBytes()));
        }catch (NoSuchAlgorithmException e){
            log.error("SHA-256 Algorithm is not support");
        }catch (NullPointerException e){
            log.debug("raw string to hash is null");
        }
        return "";
    }

    public static boolean matches(String token,String hashedToken){
        if (Objects.isNull(hashedToken)){
            return false;
        }
        return hashedToken.equals(encryptSha256(token));
    }

}
