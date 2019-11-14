package com.qinzy.admin.server.model.admin.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.*;
import com.qinzy.admin.server.config.BaseResult;
import com.qinzy.admin.server.model.admin.entity.Games;
import com.qinzy.admin.server.utils.MPage;
import com.qinzy.admin.server.model.admin.service.GamesService;

/**
* <p>
  * 拾尚包游戏表 前端控制器
  * </p>
*
* @author qinzy7@163.com
* @since 2019-11-07
*/
  @Api(tags = "拾尚包游戏表")
  @RestController
@RequestMapping("/games")
    public class GamesController {

  @Autowired
  private GamesService service;

  @ApiOperation(value = "根据id查询 拾尚包游戏表")
  @ApiResponses(
  @ApiResponse(code = 200,message = "查询成功",response = Games.class)
  )
  @GetMapping("{id}")
  public BaseResult get(@ApiParam(value = "拾尚包游戏表id") @PathVariable("id") Integer id) {
  return BaseResult.done(service.getById(id));
  }

  @ApiOperation(value = "分页查询 拾尚包游戏表")
  @ApiResponses(
  @ApiResponse(code = 200,message = "查询成功",response = Games.class)
  )
  @GetMapping("page")
  @SuppressWarnings("unchecked")
  public BaseResult page(MPage page,Games entity) {
  LambdaQueryWrapper<Games> wrapper = new LambdaQueryWrapper<>(entity);
  return BaseResult.done(service.page(page,wrapper));
  }

  @ApiOperation(value = "新增 拾尚包游戏表")
  @PostMapping("save")
  public BaseResult save(@ApiParam(value = "拾尚包游戏表") @RequestBody Games entity) {
  return service.save(entity) ? BaseResult.success("保存成功") : BaseResult.error("保存失败");
  }

  @ApiOperation(value = "修改 拾尚包游戏表")
  @PutMapping("update")
  public BaseResult update(@ApiParam(value = "拾尚包游戏表") @RequestBody Games entity) {
  return service.updateById(entity) ? BaseResult.success("保存成功") : BaseResult.error("保存失败");
  }

  @ApiOperation(value = "根据id删除 拾尚包游戏表")
  @DeleteMapping("{id}")
  public BaseResult delete(@ApiParam(value = "拾尚包游戏表id") @PathVariable("id") Integer id) {
  return service.removeById(id) ? BaseResult.success("删除成功") : BaseResult.error("删除失败");
  }

  }
