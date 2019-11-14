package com.qinzy.admin.server.sys.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.*;
import com.qinzy.admin.server.config.BaseResult;
import com.qinzy.admin.server.sys.entity.TRole;
import com.qinzy.admin.server.utils.MPage;
import com.qinzy.admin.server.sys.service.TRoleService;

/**
 * <p>
 * 后台角色表 前端控制器
 * </p>
 *
 * @author qinzy7@163.com
 * @since 2019-10-17
 */
@Api(tags = "后台角色表")
@RestController
@RequestMapping("/tRole")
public class TRoleController {

    @Autowired
    private TRoleService service;

    @ApiOperation(value = "根据id查询 后台角色表")
    @ApiResponses(
            @ApiResponse(code = 200, message = "查询成功", response = TRole.class)
    )
    @GetMapping("{id}")
    public BaseResult get(@ApiParam(value = "后台角色表id") @PathVariable("id") Integer id) {
        return BaseResult.done(service.getById(id));
    }

    @ApiOperation(value = "分页查询 后台角色表")
    @ApiResponses(
            @ApiResponse(code = 200, message = "查询成功", response = TRole.class)
    )
    @GetMapping("page")
    @SuppressWarnings("unchecked")
    public BaseResult page(MPage page, TRole entity) {
        LambdaQueryWrapper<TRole> wrapper = new LambdaQueryWrapper<>(entity);
        return BaseResult.done(service.page(page, wrapper));
    }

    @ApiOperation(value = "新增 后台角色表")
    @PostMapping("save")
    public BaseResult save(@ApiParam(value = "后台角色表") @RequestBody TRole entity) {
        return service.save(entity) ? BaseResult.success("保存成功") : BaseResult.error("保存失败");
    }

    @ApiOperation(value = "修改 后台角色表")
    @PutMapping("update")
    public BaseResult update(@ApiParam(value = "后台角色表") @RequestBody TRole entity) {
        return service.updateById(entity) ? BaseResult.success("保存成功") : BaseResult.error("保存失败");
    }

    @ApiOperation(value = "根据id删除 后台角色表")
    @DeleteMapping("{id}")
    public BaseResult delete(@ApiParam(value = "后台角色表id") @PathVariable("id") Integer id) {
        return service.removeById(id) ? BaseResult.success("删除成功") : BaseResult.error("删除失败");
    }

}
