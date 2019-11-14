package com.qinzy.admin.server.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qinzy.admin.server.config.BaseResult;
import com.qinzy.admin.server.sys.entity.TUser;
import com.qinzy.admin.server.sys.service.TUserService;
import com.qinzy.admin.server.utils.MD5Util;
import com.qinzy.admin.server.utils.MPage;
import com.qinzy.admin.server.utils.UUIDUtils;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 后台用户表 前端控制器
 * </p>
 *
 * @author qinzy7@163.com
 * @since 2019-10-15
 */
@Api(tags = "后台用户表")
@RestController
@RequestMapping("/tUser")
public class TUserController {

    @Autowired
    private TUserService tUserService;

    private final static String DEFAULT_PASSWORD = "123456";

    @ApiOperation(value = "根据id查询 后台用户表")
    @ApiResponses(
            @ApiResponse(code = 200, message = "查询成功", response = TUser.class)
    )
    @GetMapping("{id}")
    public BaseResult get(@ApiParam(value = "后台用户表id") @PathVariable("id") Integer id) {
        return BaseResult.done(tUserService.getById(id));
    }

    @ApiOperation(value = "分页查询 后台用户表")
    @ApiResponses(
            @ApiResponse(code = 200, message = "查询成功", response = TUser.class)
    )
    @GetMapping("page")
    @SuppressWarnings("unchecked")
    public BaseResult page(MPage page, TUser entity) {
        IPage list = tUserService.page(page, new QueryWrapper<TUser>()
                .select("id,real_name,user_name,phone,email,locked,create_date").lambda()
                .like(StringUtils.isNotBlank(entity.getRealName()), TUser::getRealName, entity.getRealName())
                .like(StringUtils.isNotBlank(entity.getUserName()), TUser::getUserName, entity.getUserName())
                .orderByDesc(TUser::getCreateDate));
        return BaseResult.done(list);
    }

    @ApiOperation(value = "新增 后台用户表")
    @PostMapping("save")
    public BaseResult save(@ApiParam(value = "后台用户表") @RequestBody TUser entity) {
        int count = tUserService.count(new QueryWrapper<>(new TUser().setUserName(entity.getUserName())));
        if (count > 0) {
            return BaseResult.error("用户名已存在");
        }
        entity.setSalt(UUIDUtils.getUUID());
        entity.setPassword(MD5Util.getMD5SecretKey(entity.getSalt() + DEFAULT_PASSWORD));
        return tUserService.save(entity) ? BaseResult.success("保存成功") : BaseResult.error("保存失败");
    }

    @ApiOperation(value = "修改 后台用户表")
    @PutMapping("update")
    public BaseResult update(@ApiParam(value = "后台用户表") @RequestBody TUser entity) {
        return tUserService.updateById(entity) ? BaseResult.success("保存成功") : BaseResult.error("保存失败");
    }

    @ApiOperation(value = "根据id删除 后台用户表")
    @DeleteMapping("{id}")
    public BaseResult delete(@ApiParam(value = "后台用户表id") @PathVariable("id") Integer id) {
        return tUserService.delete(id) ? BaseResult.success("删除成功") : BaseResult.error("删除失败");
    }

}
