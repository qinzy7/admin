package com.qinzy.admin.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author qinzy7@163.com
 * @since 2019-10-14
 */
@Component
public class JwtConfig {

    /**
     * jwt私钥
     */
    public static String privateKey;

    @Value("${jwt.privateKey}")
    public void setPrivateKey(String privateKey) {
        JwtConfig.privateKey = privateKey;
    }

    /**
     * jwt有效时间
     */
    public static long ttlMillis;

    @Value("${jwt.ttlMillis}")
    public void setTtlMillis(long ttlMillis) {
        JwtConfig.ttlMillis = ttlMillis;
    }
}