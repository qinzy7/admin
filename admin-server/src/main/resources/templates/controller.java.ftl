package com.qinzy.admin.server.model.admin.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.*;
import com.qinzy.admin.server.config.BaseResult;
import com.qinzy.admin.server.model.admin.entity.${entity};
import com.qinzy.admin.server.utils.MPage;
import com.qinzy.admin.server.model.admin.service.${table.serviceName};
<#if restControllerStyle>
<#else>
  import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
  import ${superControllerClassPackage};
</#if>

/**
* <p>
  * ${table.comment!} 前端控制器
  * </p>
*
* @author ${author}
* @since ${date}
*/
<#if swagger2>
  @Api(tags = "${table.comment!}")
</#if>
<#if restControllerStyle>
  @RestController
<#else>
  @Controller
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
  class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
  <#if superControllerClass??>
    public class ${table.controllerName} extends ${superControllerClass} {
  <#else>
    public class ${table.controllerName} {
  </#if>

  @Autowired
  private ${table.serviceName} service;

  @ApiOperation(value = "根据id查询 ${table.comment!}")
  @ApiResponses(
  @ApiResponse(code = 200,message = "查询成功",response = ${entity}.class)
  )
  @GetMapping("{id}")
  public BaseResult get(@ApiParam(value = "${table.comment!}id") @PathVariable("id") Integer id) {
  return BaseResult.done(service.getById(id));
  }

  @ApiOperation(value = "分页查询 ${table.comment!}")
  @ApiResponses(
  @ApiResponse(code = 200,message = "查询成功",response = ${entity}.class)
  )
  @GetMapping("page")
  @SuppressWarnings("unchecked")
  public BaseResult page(MPage page,${entity} entity) {
  LambdaQueryWrapper<${entity}> wrapper = new LambdaQueryWrapper<>(entity);
  return BaseResult.done(service.page(page,wrapper));
  }

  @ApiOperation(value = "新增 ${table.comment!}")
  @PostMapping("save")
  public BaseResult save(@ApiParam(value = "${table.comment!}") @RequestBody ${entity} entity) {
  return service.save(entity) ? BaseResult.success("保存成功") : BaseResult.error("保存失败");
  }

  @ApiOperation(value = "修改 ${table.comment!}")
  @PutMapping("update")
  public BaseResult update(@ApiParam(value = "${table.comment!}") @RequestBody ${entity} entity) {
  return service.updateById(entity) ? BaseResult.success("保存成功") : BaseResult.error("保存失败");
  }

  @ApiOperation(value = "根据id删除 ${table.comment!}")
  @DeleteMapping("{id}")
  public BaseResult delete(@ApiParam(value = "${table.comment!}id") @PathVariable("id") Integer id) {
  return service.removeById(id) ? BaseResult.success("删除成功") : BaseResult.error("删除失败");
  }

  }
</#if>
