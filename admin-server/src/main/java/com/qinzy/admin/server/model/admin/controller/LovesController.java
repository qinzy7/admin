package com.qinzy.admin.server.model.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qinzy.admin.server.annotation.LogAnnotation;
import com.qinzy.admin.server.config.BaseResult;
import com.qinzy.admin.server.model.admin.entity.Loves;
import com.qinzy.admin.server.model.admin.service.LovesService;
import com.qinzy.admin.server.utils.MPage;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 土味情话表 前端控制器
 * </p>
 *
 * @author qinzy7@163.com
 * @since 2019-11-01
 */
@Api(tags = "土味情话表")
@RestController
@RequestMapping("/loves")
@Slf4j
public class LovesController {

    @Autowired
    private LovesService service;

    @ApiOperation(value = "根据id查询 土味情话表")
    @ApiResponses(
            @ApiResponse(code = 200, message = "查询成功", response = Loves.class)
    )
    @GetMapping("{id}")
    public BaseResult get(@ApiParam(value = "土味情话表id") @PathVariable("id") Integer id) {
        return BaseResult.done(service.getById(id));
    }

    @ApiOperation(value = "分页查询 土味情话表")
    @ApiResponses(
            @ApiResponse(code = 200, message = "查询成功", response = Loves.class)
    )
    @LogAnnotation(
            menu = "土味情话",
            action = "查询",
            operation = "分页查询土味情话列表"
    )
    @GetMapping("page")
    @SuppressWarnings("unchecked")
    public BaseResult page(MPage page, Loves entity) {
        log.info("==================查询土味情话分页列表start==================");
        LambdaQueryWrapper<Loves> wrapper = new LambdaQueryWrapper<>(entity);
        IPage<Loves> list = service.page(page, wrapper);
        log.info("数据条数：{}", list.getRecords().size());
        log.info("==================查询土味情话分页列表end==================");
        return BaseResult.done(list);
    }

    @ApiOperation(value = "新增 土味情话表")
    @PostMapping("save")
    public BaseResult save(@ApiParam(value = "土味情话表") @RequestBody Loves entity) {
        return service.save(entity) ? BaseResult.success("保存成功") : BaseResult.error("保存失败");
    }

    @ApiOperation(value = "修改 土味情话表")
    @PutMapping("update")
    public BaseResult update(@ApiParam(value = "土味情话表") @RequestBody Loves entity) {
        return service.updateById(entity) ? BaseResult.success("保存成功") : BaseResult.error("保存失败");
    }

    @ApiOperation(value = "根据id删除 土味情话表")
    @DeleteMapping("{id}")
    public BaseResult delete(@ApiParam(value = "土味情话表id") @PathVariable("id") Integer id) {
        return service.removeById(id) ? BaseResult.success("删除成功") : BaseResult.error("删除失败");
    }

}
