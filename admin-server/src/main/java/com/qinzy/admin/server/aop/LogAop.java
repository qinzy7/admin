package com.qinzy.admin.server.aop;

import com.qinzy.admin.server.annotation.LogAnnotation;
import com.qinzy.admin.server.model.admin.entity.LogEntity;
import com.qinzy.admin.server.sys.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.CompletableFuture;

/**
 * @author kejiang
 * @since 2019-11-05
 */
@Aspect
@Component
@Slf4j
public class LogAop extends BaseController {

    /**
     * 保存操作日志
     */
    @Around(value = "@annotation(com.qinzy.admin.server.annotation.LogAnnotation)")
    public Object saveLog(ProceedingJoinPoint joinPoint) throws Throwable {

        // 获得注解注释的方法
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        LogAnnotation logAnnotation = methodSignature.getMethod().getDeclaredAnnotation(LogAnnotation.class);
        //新建日志实体
        try {
            LogEntity logEntity = new LogEntity()
                    .setMenu(logAnnotation.menu())
                    .setAction(logAnnotation.action())
                    .setOperation(logAnnotation.operation())
                    .setRequestDate(new Date())
                    .setRequestUserId(super.getLoginUserId())
                    .setRequestIp(super.getRequestIp());
            log.info("==================执行日志保存操作start==================");
            boolean insert = logEntity.insert();

            log.info(insert ? "日志保存成功" : "日志保存失败");
            log.info("==================执行日志保存操作end==================");
            //执行原方法
            return joinPoint.proceed();
        } catch (Exception e) {
            log.error("保存日志方法执行失败：", e);
            throw e;
        } finally {
            CompletableFuture.runAsync(() -> {
                try {
                    log.info("异步将Log对象发送到队列成功");
                } catch (Exception e) {
                    log.info("异步将Log对象发送到队列异常", e);
                }
            });
        }
    }

}