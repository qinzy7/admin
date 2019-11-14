package com.qinzy.admin.server.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qinzy.admin.server.annotation.PassToken;
import com.qinzy.admin.server.annotation.UserLoginToken;
import com.qinzy.admin.server.config.BaseResult;
import com.qinzy.admin.server.sys.entity.TMenu;
import com.qinzy.admin.server.sys.entity.TUser;
import com.qinzy.admin.server.sys.entity.dto.LoginDto;
import com.qinzy.admin.server.sys.service.TUserService;
import com.qinzy.admin.server.utils.CheckUtils;
import com.qinzy.admin.server.utils.JwtUtils;
import com.qinzy.admin.server.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author qinzy7@163.com
 * @since 2019-10-10
 */
@RestController
@Slf4j
public class LoginController extends BaseController {

    private final TUserService tUserService;

    public LoginController(TUserService tUserService) {
        this.tUserService = tUserService;
    }

    /**
     * 登录接口
     * @param loginDto 登录参数
     * @param br 数据格式校验
     * @return 返回登录成功信息以及token
     */
    @PostMapping("login")
    @PassToken
    public Object login(@Validated @RequestBody LoginDto loginDto, BindingResult br) {
        log.debug("[START] -- login function");
        //数据校验START
        BaseResult check = CheckUtils.check(br);
        if (check != null) {
            log.error("数据校验异常");
            log.debug("[END] -- login function");
            return check;
        }
        log.debug("userName:{}", loginDto.getUserName());
        log.debug("password:{}", loginDto.getPassword());
        TUser user = tUserService.getOne(new QueryWrapper<>(new TUser().setUserName(loginDto.getUserName())));
        if (user == null) {
            return BaseResult.error("用户名不存在");
        }
        if (!user.getPassword().equals(MD5Util.getMD5SecretKey(user.getSalt() + loginDto.getPassword()))) {
            return BaseResult.error("密码错误");
        }
        if (0 != user.getLocked()) {
            return BaseResult.error("用户已被锁定");
        }
        String token = JwtUtils.createJWT(String.valueOf(user.getId()), user.getUserName());
        log.debug("[END] -- login function");
        return BaseResult.done("登陆成功", token);
    }

    /**
     * 查询登录用户菜单树（模式1：查询出用户所有的角色所拥有所有的菜单）
     * @return 菜单树
     */
    @GetMapping("loginUser/menu")
    @UserLoginToken
    public BaseResult getUserInfo() {
        Integer userId = super.getLoginUserId();
        List<TMenu> list = tUserService.getMenuTreeByUserId(userId, null);
        return BaseResult.done(list);
    }

    /**
     * 查询登录用户菜单树（模式1：根据角色查询菜单）
     * @return 菜单树
     */
    @GetMapping("loginUser/menu/{roleId}")
    @UserLoginToken
    public BaseResult getUserInfo(@PathVariable Integer roleId) {
        Integer userId = super.getLoginUserId();
        List<TMenu> list = tUserService.getMenuTreeByUserId(userId, roleId);
        return BaseResult.done(list);
    }
}