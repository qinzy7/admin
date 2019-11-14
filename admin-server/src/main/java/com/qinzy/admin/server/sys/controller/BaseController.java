package com.qinzy.admin.server.sys.controller;

import com.qinzy.admin.server.config.JwtCheckResult;
import com.qinzy.admin.server.utils.JwtUtils;
import io.jsonwebtoken.JwtException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author qinzy7@163.com
 * @since 2019-10-16
 */
@Component
public class BaseController {

    /**
     * 获取当前登录用户id
     * @return 登录用户id
     */
    protected Integer getLoginUserId() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            throw new JwtException("token不能为空");
        }
        JwtCheckResult result = JwtUtils.validateJWT(token);
        if (result.getCode() != 0) {
            throw new JwtException("token认证失败请重新登录");
        }
        return Integer.valueOf(result.getClaims().getId());
    }

    /**
     * 获取请求ip
     * @return ip
     */
    protected String getRequestIp() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getRemoteAddr();
    }
}