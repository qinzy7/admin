package com.qinzy.admin.server.config;

import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author qinzy7@163.com
 * @since 2019-10-14
 */
@Data
@AllArgsConstructor
public class JwtCheckResult {
    private Integer code;
    private String msg;
    private Claims claims;

    private JwtCheckResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static JwtCheckResult success(Claims claims) {
        return new JwtCheckResult(0, "用户校验成功", claims);
    }

    public static JwtCheckResult error() {
        return new JwtCheckResult(401, "用户校验异常，请重新登录");
    }

    public static JwtCheckResult error(String msg) {
        return new JwtCheckResult(401, msg);
    }
}