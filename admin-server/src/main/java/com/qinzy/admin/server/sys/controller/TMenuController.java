package com.qinzy.admin.server.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.qinzy.admin.server.config.BaseResult;
import com.qinzy.admin.server.sys.entity.TMenu;
import com.qinzy.admin.server.sys.service.TMenuService;
import com.qinzy.admin.server.utils.MPage;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 后台菜单表 前端控制器
 * </p>
 *
 * @author qinzy7@163.com
 * @since 2019-10-17
 */
@Api(tags = "后台菜单表")
@RestController
@RequestMapping("/tMenu")
public class TMenuController {

    @Autowired
    private TMenuService tMenuService;

    @ApiOperation(value = "根据id查询 后台菜单表")
    @ApiResponses(
            @ApiResponse(code = 200, message = "查询成功", response = TMenu.class)
    )
    @GetMapping("{id}")
    public BaseResult get(@ApiParam(value = "后台菜单表id") @PathVariable("id") Integer id) {
        return BaseResult.done(tMenuService.getById(id));
    }

    @ApiOperation(value = "分页查询 后台菜单表")
    @ApiResponses(
            @ApiResponse(code = 200, message = "查询成功", response = TMenu.class)
    )
    @GetMapping("page")
    @SuppressWarnings("unchecked")
    public BaseResult page(MPage page, TMenu entity) {
        return BaseResult.done(tMenuService.page(page, new LambdaQueryWrapper<TMenu>()
                .eq(TMenu::getParentId, entity.getParentId())
                .like(StringUtils.isNotBlank(entity.getName()), TMenu::getName, entity.getName())
                .orderByAsc(TMenu::getSort)));
    }

    @ApiOperation(value = "菜单树 后台菜单表")
    @ApiResponses(
            @ApiResponse(code = 200, message = "查询成功", response = TMenu.class)
    )
    @GetMapping("tree")
    public BaseResult tree() {
        return BaseResult.done(tMenuService.getByParentId(0));
    }

    @ApiOperation(value = "新增 后台菜单表")
    @PostMapping("save")
    public BaseResult save(@ApiParam(value = "后台菜单表") @RequestBody TMenu entity) {
        return tMenuService.save(entity) ? BaseResult.success("保存成功") : BaseResult.error("保存失败");
    }

    @ApiOperation(value = "修改 后台菜单表")
    @PutMapping("update")
    public BaseResult update(@ApiParam(value = "后台菜单表") @RequestBody TMenu entity) {
        return tMenuService.updateById(entity) ? BaseResult.success("保存成功") : BaseResult.error("保存失败");
    }

    @ApiOperation(value = "根据id删除 后台菜单表")
    @DeleteMapping("{id}")
    public BaseResult delete(@ApiParam(value = "后台菜单表id") @PathVariable("id") Integer id) {
        return tMenuService.delete(id) ? BaseResult.success("删除成功") : BaseResult.error("删除失败");
    }

}
