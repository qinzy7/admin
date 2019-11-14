package com.qinzy.admin.server.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qinzy.admin.server.sys.entity.TRole;
import com.qinzy.admin.server.sys.entity.TUser;

import java.util.List;

/**
 * <p>
 * 后台用户表 Mapper 接口
 * </p>
 *
 * @author qinzy7@163.com
 * @since 2019-10-15
 */
public interface TUserMapper extends BaseMapper<TUser> {

    /**
     * 根据用户id查询角色ids
     * @param id 用户id
     * @return 角色ids
     */
    List<Integer> getRoleIdsByUserId(Integer id);

    /**
     * 根据角色id查询角色信息和菜单
     * @param roleId 角色id
     * @return 角色信息和菜单
     */
    TRole getRoleByRoleId(Integer roleId);
}
