package com.qinzy.admin.server.model.admin.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.*;
import com.qinzy.admin.server.config.BaseResult;
import com.qinzy.admin.server.model.admin.entity.GameWinningRules;
import com.qinzy.admin.server.utils.MPage;
import com.qinzy.admin.server.model.admin.service.GameWinningRulesService;

/**
* <p>
  * 游戏中奖规则表 前端控制器
  * </p>
*
* @author qinzy7@163.com
* @since 2019-11-07
*/
  @Api(tags = "游戏中奖规则表")
  @RestController
@RequestMapping("/gameWinningRules")
    public class GameWinningRulesController {

  @Autowired
  private GameWinningRulesService service;

  @ApiOperation(value = "根据id查询 游戏中奖规则表")
  @ApiResponses(
  @ApiResponse(code = 200,message = "查询成功",response = GameWinningRules.class)
  )
  @GetMapping("{id}")
  public BaseResult get(@ApiParam(value = "游戏中奖规则表id") @PathVariable("id") Integer id) {
  return BaseResult.done(service.getById(id));
  }

  @ApiOperation(value = "分页查询 游戏中奖规则表")
  @ApiResponses(
  @ApiResponse(code = 200,message = "查询成功",response = GameWinningRules.class)
  )
  @GetMapping("page")
  @SuppressWarnings("unchecked")
  public BaseResult page(MPage page,GameWinningRules entity) {
  LambdaQueryWrapper<GameWinningRules> wrapper = new LambdaQueryWrapper<>(entity);
  return BaseResult.done(service.page(page,wrapper));
  }

  @ApiOperation(value = "新增 游戏中奖规则表")
  @PostMapping("save")
  public BaseResult save(@ApiParam(value = "游戏中奖规则表") @RequestBody GameWinningRules entity) {
  return service.save(entity) ? BaseResult.success("保存成功") : BaseResult.error("保存失败");
  }

  @ApiOperation(value = "修改 游戏中奖规则表")
  @PutMapping("update")
  public BaseResult update(@ApiParam(value = "游戏中奖规则表") @RequestBody GameWinningRules entity) {
  return service.updateById(entity) ? BaseResult.success("保存成功") : BaseResult.error("保存失败");
  }

  @ApiOperation(value = "根据id删除 游戏中奖规则表")
  @DeleteMapping("{id}")
  public BaseResult delete(@ApiParam(value = "游戏中奖规则表id") @PathVariable("id") Integer id) {
  return service.removeById(id) ? BaseResult.success("删除成功") : BaseResult.error("删除失败");
  }

  }
