package com.qinzy.admin.server.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qinzy.admin.server.sys.entity.TRole;

import java.util.List;

/**
 * <p>
 * 后台角色表 Mapper 接口
 * </p>
 *
 * @author qinzy7@163.com
 * @since 2019-10-17
 */
public interface TRoleMapper extends BaseMapper<TRole> {

    /**
     * 删除角色用户关联信息:byRoleId
     * @param roleId 角色id
     */
    void deleteRoleUserByRoleId(Integer roleId);

    /**
     * 删除角色用户关联信息:byUserId
     * @param userId 用户id
     */
    void deleteRoleUserByUserId(Integer userId);

    /**
     * 删除角色菜单关联信息:byRoleIds
     * @param roleIds 角色ids
     */
    void deleteRoleMenuByRoleIds(List<Integer> roleIds);

    /**
     * 删除角色菜单关联信息:byMenuIds
     * @param menuIds 菜单ids
     */
    void deleteRoleMenuByMenuIds(List<Integer> menuIds);
}
