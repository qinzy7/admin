package com.qinzy.admin.server.model.admin.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.*;
import com.qinzy.admin.server.config.BaseResult;
import com.qinzy.admin.server.model.admin.entity.UserTask;
import com.qinzy.admin.server.utils.MPage;
import com.qinzy.admin.server.model.admin.service.UserTaskService;

/**
* <p>
  * 用户完成任务纪录表 前端控制器
  * </p>
*
* @author qinzy7@163.com
* @since 2019-11-07
*/
  @Api(tags = "用户完成任务纪录表")
  @RestController
@RequestMapping("/userTask")
    public class UserTaskController {

  @Autowired
  private UserTaskService service;

  @ApiOperation(value = "根据id查询 用户完成任务纪录表")
  @ApiResponses(
  @ApiResponse(code = 200,message = "查询成功",response = UserTask.class)
  )
  @GetMapping("{id}")
  public BaseResult get(@ApiParam(value = "用户完成任务纪录表id") @PathVariable("id") Integer id) {
  return BaseResult.done(service.getById(id));
  }

  @ApiOperation(value = "分页查询 用户完成任务纪录表")
  @ApiResponses(
  @ApiResponse(code = 200,message = "查询成功",response = UserTask.class)
  )
  @GetMapping("page")
  @SuppressWarnings("unchecked")
  public BaseResult page(MPage page,UserTask entity) {
  LambdaQueryWrapper<UserTask> wrapper = new LambdaQueryWrapper<>(entity);
  return BaseResult.done(service.page(page,wrapper));
  }

  @ApiOperation(value = "新增 用户完成任务纪录表")
  @PostMapping("save")
  public BaseResult save(@ApiParam(value = "用户完成任务纪录表") @RequestBody UserTask entity) {
  return service.save(entity) ? BaseResult.success("保存成功") : BaseResult.error("保存失败");
  }

  @ApiOperation(value = "修改 用户完成任务纪录表")
  @PutMapping("update")
  public BaseResult update(@ApiParam(value = "用户完成任务纪录表") @RequestBody UserTask entity) {
  return service.updateById(entity) ? BaseResult.success("保存成功") : BaseResult.error("保存失败");
  }

  @ApiOperation(value = "根据id删除 用户完成任务纪录表")
  @DeleteMapping("{id}")
  public BaseResult delete(@ApiParam(value = "用户完成任务纪录表id") @PathVariable("id") Integer id) {
  return service.removeById(id) ? BaseResult.success("删除成功") : BaseResult.error("删除失败");
  }

  }
