package com.qinzy.admin.server.sys.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 登录参数类
 * @author qinzy7@163.com
 * @since 2019-10-15
 */
@Data
public class LoginDto {

    @NotBlank(message = "用户名不能为空")
    private String userName;

    @NotBlank(message = "密码不能为空")
    private String password;
}