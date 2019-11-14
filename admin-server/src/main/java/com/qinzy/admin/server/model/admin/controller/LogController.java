package com.qinzy.admin.server.model.admin.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.*;
import com.qinzy.admin.server.config.BaseResult;
import com.qinzy.admin.server.model.admin.entity.LogEntity;
import com.qinzy.admin.server.utils.MPage;
import com.qinzy.admin.server.model.admin.service.LogService;

/**
 * <p>
 * 操作日志表 前端控制器
 * </p>
 *
 * @author qinzy7@163.com
 * @since 2019-11-06
 */
@Api(tags = "操作日志表")
@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    private LogService service;

    @ApiOperation(value = "根据id查询 操作日志表")
    @ApiResponses(
            @ApiResponse(code = 200, message = "查询成功", response = LogEntity.class)
    )
    @GetMapping("{id}")
    public BaseResult get(@ApiParam(value = "操作日志表id") @PathVariable("id") Integer id) {
        return BaseResult.done(service.getById(id));
    }

    @ApiOperation(value = "分页查询 操作日志表")
    @ApiResponses(
            @ApiResponse(code = 200, message = "查询成功", response = LogEntity.class)
    )
    @GetMapping("page")
    @SuppressWarnings("unchecked")
    public BaseResult page(MPage page, LogEntity entity) {
        LambdaQueryWrapper<LogEntity> wrapper = new LambdaQueryWrapper<>(entity);
        return BaseResult.done(service.page(page, wrapper));
    }

    @ApiOperation(value = "新增 操作日志表")
    @PostMapping("save")
    public BaseResult save(@ApiParam(value = "操作日志表") @RequestBody LogEntity entity) {
        return service.save(entity) ? BaseResult.success("保存成功") : BaseResult.error("保存失败");
    }

    @ApiOperation(value = "修改 操作日志表")
    @PutMapping("update")
    public BaseResult update(@ApiParam(value = "操作日志表") @RequestBody LogEntity entity) {
        return service.updateById(entity) ? BaseResult.success("保存成功") : BaseResult.error("保存失败");
    }

    @ApiOperation(value = "根据id删除 操作日志表")
    @DeleteMapping("{id}")
    public BaseResult delete(@ApiParam(value = "操作日志表id") @PathVariable("id") Integer id) {
        return service.removeById(id) ? BaseResult.success("删除成功") : BaseResult.error("删除失败");
    }

}
