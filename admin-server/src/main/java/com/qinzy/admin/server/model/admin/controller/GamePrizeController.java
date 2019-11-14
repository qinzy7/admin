package com.qinzy.admin.server.model.admin.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.*;
import com.qinzy.admin.server.config.BaseResult;
import com.qinzy.admin.server.model.admin.entity.GamePrize;
import com.qinzy.admin.server.utils.MPage;
import com.qinzy.admin.server.model.admin.service.GamePrizeService;

/**
* <p>
  * 游戏奖品表 前端控制器
  * </p>
*
* @author qinzy7@163.com
* @since 2019-11-07
*/
  @Api(tags = "游戏奖品表")
  @RestController
@RequestMapping("/gamePrize")
    public class GamePrizeController {

  @Autowired
  private GamePrizeService service;

  @ApiOperation(value = "根据id查询 游戏奖品表")
  @ApiResponses(
  @ApiResponse(code = 200,message = "查询成功",response = GamePrize.class)
  )
  @GetMapping("{id}")
  public BaseResult get(@ApiParam(value = "游戏奖品表id") @PathVariable("id") Integer id) {
  return BaseResult.done(service.getById(id));
  }

  @ApiOperation(value = "分页查询 游戏奖品表")
  @ApiResponses(
  @ApiResponse(code = 200,message = "查询成功",response = GamePrize.class)
  )
  @GetMapping("page")
  @SuppressWarnings("unchecked")
  public BaseResult page(MPage page,GamePrize entity) {
  LambdaQueryWrapper<GamePrize> wrapper = new LambdaQueryWrapper<>(entity);
  return BaseResult.done(service.page(page,wrapper));
  }

  @ApiOperation(value = "新增 游戏奖品表")
  @PostMapping("save")
  public BaseResult save(@ApiParam(value = "游戏奖品表") @RequestBody GamePrize entity) {
  return service.save(entity) ? BaseResult.success("保存成功") : BaseResult.error("保存失败");
  }

  @ApiOperation(value = "修改 游戏奖品表")
  @PutMapping("update")
  public BaseResult update(@ApiParam(value = "游戏奖品表") @RequestBody GamePrize entity) {
  return service.updateById(entity) ? BaseResult.success("保存成功") : BaseResult.error("保存失败");
  }

  @ApiOperation(value = "根据id删除 游戏奖品表")
  @DeleteMapping("{id}")
  public BaseResult delete(@ApiParam(value = "游戏奖品表id") @PathVariable("id") Integer id) {
  return service.removeById(id) ? BaseResult.success("删除成功") : BaseResult.error("删除失败");
  }

  }
