package com.qinzy.admin.server.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qinzy.admin.server.sys.entity.TMenu;

import java.util.List;

/**
 * <p>
 * 后台菜单表 服务类
 * </p>
 *
 * @author qinzy7@163.com
 * @since 2019-10-17
 */
public interface TMenuService extends IService<TMenu> {

    /**
     * 查询菜单树
     * @param parentId 0
     * @return 菜单树
     */
    List<TMenu> getByParentId(Integer parentId);

    /**
     * 根据菜单id删除菜单，子菜单，以及菜单关联角色
     * @param id 菜单id
     * @return true：成功， false：失败
     */
    boolean delete(Integer id);
}
