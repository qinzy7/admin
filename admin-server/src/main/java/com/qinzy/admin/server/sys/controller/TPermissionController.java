package com.qinzy.admin.server.sys.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.*;
import com.qinzy.admin.server.config.BaseResult;
import com.qinzy.admin.server.sys.entity.TPermission;
import com.qinzy.admin.server.utils.MPage;
import com.qinzy.admin.server.sys.service.TPermissionService;

/**
 * <p>
 * 后台权限表 前端控制器
 * </p>
 *
 * @author qinzy7@163.com
 * @since 2019-10-17
 */
@Api(tags = "后台权限表")
@RestController
@RequestMapping("/tPermission")
public class TPermissionController {

    @Autowired
    private TPermissionService service;

    @ApiOperation(value = "根据id查询 后台权限表")
    @ApiResponses(
            @ApiResponse(code = 200, message = "查询成功", response = TPermission.class)
    )
    @GetMapping("{id}")
    public BaseResult get(@ApiParam(value = "后台权限表id") @PathVariable("id") Integer id) {
        return BaseResult.done(service.getById(id));
    }

    @ApiOperation(value = "分页查询 后台权限表")
    @ApiResponses(
            @ApiResponse(code = 200, message = "查询成功", response = TPermission.class)
    )
    @GetMapping("page")
    @SuppressWarnings("unchecked")
    public BaseResult page(MPage page, TPermission entity) {
        LambdaQueryWrapper<TPermission> wrapper = new LambdaQueryWrapper<>(entity);
        return BaseResult.done(service.page(page, wrapper));
    }

    @ApiOperation(value = "新增 后台权限表")
    @PostMapping("save")
    public BaseResult save(@ApiParam(value = "后台权限表") @RequestBody TPermission entity) {
        return service.save(entity) ? BaseResult.success("保存成功") : BaseResult.error("保存失败");
    }

    @ApiOperation(value = "修改 后台权限表")
    @PutMapping("update")
    public BaseResult update(@ApiParam(value = "后台权限表") @RequestBody TPermission entity) {
        return service.updateById(entity) ? BaseResult.success("保存成功") : BaseResult.error("保存失败");
    }

    @ApiOperation(value = "根据id删除 后台权限表")
    @DeleteMapping("{id}")
    public BaseResult delete(@ApiParam(value = "后台权限表id") @PathVariable("id") Integer id) {
        return service.removeById(id) ? BaseResult.success("删除成功") : BaseResult.error("删除失败");
    }

}
