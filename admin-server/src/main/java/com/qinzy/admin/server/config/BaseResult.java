package com.qinzy.admin.server.config;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author qinzy7@163.com
 * @since 2019-10-15
 */
@Data
@AllArgsConstructor
public class BaseResult {

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 具体内容
     */
    private Object data;

    /**
     * 请求成功返回数据，自定义code,msg
     */
    public static BaseResult done(Integer code, String msg, Object object) {
        return new BaseResult(code, msg, object);
    }

    /**
     * 请求成功返回数据，自定义msg
     */
    public static BaseResult done(String msg, Object object) {
        return done(0, msg, object);
    }

    /**
     * 请求成功返回数据
     */
    public static BaseResult done(Object object) {
        return done(0, "请求成功", object);
    }

    /**
     * 请求成功自定义code,msg
     */
    public static BaseResult success(Integer code, String msg) {
        return new BaseResult(code, msg, null);
    }

    /**
     * 请求成功自定义msg
     */
    public static BaseResult success(String msg) {
        return success(0, msg);
    }

    /**
     * 默认返回请求成功
     */
    public static BaseResult success() {
        return success(0, "请求成功");
    }

    /**
     * 请求失败自定义code,msg
     */
    public static BaseResult error(Integer code, String msg) {
        return new BaseResult(code, msg, null);
    }

    /**
     * 请求失败自定义msg
     */
    public static BaseResult error(String msg) {
        return error(1, msg);
    }

    /**
     * 默认返回请求失败
     */
    public static BaseResult error() {
        return error(1, "请求失败");
    }

}