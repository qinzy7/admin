package com.qinzy.admin.server.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qinzy.admin.server.sys.entity.TMenu;
import com.qinzy.admin.server.sys.entity.TUser;
import com.qinzy.admin.server.sys.mapper.TMenuMapper;
import com.qinzy.admin.server.sys.mapper.TRoleMapper;
import com.qinzy.admin.server.sys.mapper.TUserMapper;
import com.qinzy.admin.server.sys.service.TUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 后台用户表 服务实现类
 * </p>
 *
 * @author qinzy7@163.com
 * @since 2019-10-15
 */
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements TUserService {

    private final TUserMapper tUserMapper;
    private final TRoleMapper tRoleMapper;
    private final TMenuMapper tMenuMapper;

    public TUserServiceImpl(TUserMapper tUserMapper
            , TRoleMapper tRoleMapper
            , TMenuMapper tMenuMapper) {
        this.tUserMapper = tUserMapper;
        this.tRoleMapper = tRoleMapper;
        this.tMenuMapper = tMenuMapper;
    }

    /**
     * 删除用户信息以及用户角色关联信息
     *
     * @param id 用户id
     * @return true
     */
    @Override
    @Transactional
    public boolean delete(Integer id) {
        tUserMapper.deleteById(id);
        tRoleMapper.deleteRoleUserByUserId(id);
        return true;
    }

    /**
     * 查询登录用户菜单树
     *
     * @param userId 用户id
     * @param roleId 角色id
     * @return 菜单树
     */
    @Override
    public List<TMenu> getMenuTreeByUserId(Integer userId, Integer roleId) {
        //初始化菜单树
        List<TMenu> menuTree = null;
        //初始化菜单id Set集合
        Set<Integer> menuIds = new HashSet<>();

        if (roleId == null) {
            //查询用户所有的角色id
            List<Integer> roleIds = tUserMapper.getRoleIdsByUserId(userId);
            for (Integer id : roleIds) {
                //查询出所有角色下的所有菜单并添加到set集合：Set.addAll方法应该是会去重，待验证
                menuIds.addAll(tUserMapper.getRoleByRoleId(id).getMenuIds());
            }
        } else {
            menuIds.addAll(tUserMapper.getRoleByRoleId(roleId).getMenuIds());
        }

        //循环出所有的菜单id
        List<Integer> ids = new ArrayList<>(menuIds);

        if (ids.size() > 0) {
            //根据父id与菜单id查询菜单树：此方法适用于菜单层数为2级的情况
            //查询出1级菜单列表
            menuTree = tMenuMapper.selectList(new QueryWrapper<TMenu>()
                    .select("id, name, parent_id, url, sort").lambda()
                    .eq(TMenu::getParentId, 0)
                    .in(TMenu::getId, ids)
                    .orderByAsc(TMenu::getSort));
            listTree(menuTree, ids);
        }

        return menuTree;
    }

    /**
     * 循环菜单树
     *
     * @param list 父菜单list
     * @param ids  菜单ids
     */
    private void listTree(List<TMenu> list, List<Integer> ids) {
        for (TMenu menu : list) {
            List<TMenu> ts = tMenuMapper.selectList(new QueryWrapper<TMenu>()
                    .select("id, name, parent_id, url, sort").lambda()
                    .eq(TMenu::getParentId, menu.getId())
                    .in(TMenu::getId, ids)
                    .orderByAsc(TMenu::getSort));
            // 如果存在3级菜单，把以下放开即可，不存在3级菜单就注释掉，不然影响效率
//            listTree(ts, ids);
            menu.setChildren(ts);
        }
    }
}
