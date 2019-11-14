package com.qinzy.admin.server.model.admin.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.*;
import com.qinzy.admin.server.config.BaseResult;
import com.qinzy.admin.server.model.admin.entity.GameTask;
import com.qinzy.admin.server.utils.MPage;
import com.qinzy.admin.server.model.admin.service.GameTaskService;

/**
* <p>
  * 游戏任务表 前端控制器
  * </p>
*
* @author qinzy7@163.com
* @since 2019-11-07
*/
  @Api(tags = "游戏任务表")
  @RestController
@RequestMapping("/gameTask")
    public class GameTaskController {

  @Autowired
  private GameTaskService service;

  @ApiOperation(value = "根据id查询 游戏任务表")
  @ApiResponses(
  @ApiResponse(code = 200,message = "查询成功",response = GameTask.class)
  )
  @GetMapping("{id}")
  public BaseResult get(@ApiParam(value = "游戏任务表id") @PathVariable("id") Integer id) {
  return BaseResult.done(service.getById(id));
  }

  @ApiOperation(value = "分页查询 游戏任务表")
  @ApiResponses(
  @ApiResponse(code = 200,message = "查询成功",response = GameTask.class)
  )
  @GetMapping("page")
  @SuppressWarnings("unchecked")
  public BaseResult page(MPage page,GameTask entity) {
  LambdaQueryWrapper<GameTask> wrapper = new LambdaQueryWrapper<>(entity);
  return BaseResult.done(service.page(page,wrapper));
  }

  @ApiOperation(value = "新增 游戏任务表")
  @PostMapping("save")
  public BaseResult save(@ApiParam(value = "游戏任务表") @RequestBody GameTask entity) {
  return service.save(entity) ? BaseResult.success("保存成功") : BaseResult.error("保存失败");
  }

  @ApiOperation(value = "修改 游戏任务表")
  @PutMapping("update")
  public BaseResult update(@ApiParam(value = "游戏任务表") @RequestBody GameTask entity) {
  return service.updateById(entity) ? BaseResult.success("保存成功") : BaseResult.error("保存失败");
  }

  @ApiOperation(value = "根据id删除 游戏任务表")
  @DeleteMapping("{id}")
  public BaseResult delete(@ApiParam(value = "游戏任务表id") @PathVariable("id") Integer id) {
  return service.removeById(id) ? BaseResult.success("删除成功") : BaseResult.error("删除失败");
  }

  }
