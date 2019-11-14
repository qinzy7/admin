package com.qinzy.admin.server.utils;

import com.qinzy.admin.server.config.JwtCheckResult;
import com.qinzy.admin.server.config.JwtConfig;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

/**
 * jwt权限认证
 *
 * @author qinzy7@163.com
 * @since 2019-10-12
 */
@Slf4j
public class JwtUtils {

    /**
     * 签发JWT
     *
     * @param id      jti jwt唯一标识
     * @param subject 可以是JSON数据 尽可能少
     * @return Jwt字符串
     */
    public static String createJWT(String id, String subject) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        SecretKey secretKey = generalKey();
        long expMillis = nowMillis + JwtConfig.ttlMillis * 60 * 1000;
        Date expDate = new Date(expMillis);
        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setSubject(subject)    // 主题
                .setIssuer("system")    // 签发者
                .setIssuedAt(now)       // 签发时间
                .setExpiration(expDate) // 过期时间
                .signWith(signatureAlgorithm, secretKey); // 签名算法以及密匙
//        if (ttlMillis >= 0) {
//            long expMillis = nowMillis + ttlMillis;
//            Date expDate = new Date(expMillis);
//            builder.setExpiration(expDate); // 过期时间
//        }
        return builder.compact();
    }

    /**
     * 验证JWT
     *
     * @param token 登录生成的token
     * @return 返回验证结果
     */
    public static JwtCheckResult validateJWT(String token) {
        Claims claims;
        JwtCheckResult result;
        try {
            claims = parseJWT(token);
            log.debug("jwt校验成功");
            result = JwtCheckResult.success(claims);
        } catch (ExpiredJwtException e) {
            log.error("token过期", e);
            result = JwtCheckResult.error("token过期，请重新登录");
        } catch (SignatureException e) {
            log.error("无效的token", e);
            result = JwtCheckResult.error("无效的token，请重新登录");
        } catch (Exception e) {
            log.error("jwt校验失败，请重新登录", e);
            result = JwtCheckResult.error();
        }
        return result;
    }

    private static SecretKey generalKey() {
        byte[] encodedKey = Base64.decodeBase64(JwtConfig.privateKey);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }

    /**
     * 解析JWT字符串
     *
     * @param token token
     * @return 返回Claims
     */
    private static Claims parseJWT(String token) {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }
}