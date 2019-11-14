package com.qinzy.admin.server.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qinzy.admin.server.sys.entity.TMenu;
import com.qinzy.admin.server.sys.entity.TUser;

import java.util.List;

/**
 * <p>
 * 后台用户表 服务类
 * </p>
 *
 * @author qinzy7@163.com
 * @since 2019-10-15
 */
public interface TUserService extends IService<TUser> {

    /**
     * 删除角色用户关联信息
     * @param id 用户id
     * @return true
     */
    boolean delete(Integer id);

    /**
     * 查询登录用户菜单树
     * @param userId 用户id
     * @param roleId 角色id
     * @return 菜单树
     */
    List<TMenu> getMenuTreeByUserId(Integer userId, Integer roleId);
}
