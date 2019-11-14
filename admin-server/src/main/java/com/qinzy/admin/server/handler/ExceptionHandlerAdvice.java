package com.qinzy.admin.server.handler;

import com.qinzy.admin.server.config.BaseResult;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author qinzy7@163.com
 * @since 2019-10-15
 */
@RestControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {

    /**
     * 全局捕获JwtException
     * @param request 请求request
     * @param e 异常
     * @return 返回错误json格式信息
     */
    @ExceptionHandler(JwtException.class)
    public BaseResult jwtException(HttpServletRequest request, JwtException e) {
        log.error("JwtException.url:{}", request.getRequestURL());
        log.error("JwtException.ERROR:", e);
        return BaseResult.error(401, e.getMessage());
    }
}