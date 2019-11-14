package com.qinzy.admin.server.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qinzy.admin.server.sys.entity.TMenu;
import com.qinzy.admin.server.sys.mapper.TMenuMapper;
import com.qinzy.admin.server.sys.mapper.TRoleMapper;
import com.qinzy.admin.server.sys.service.TMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 后台菜单表 服务实现类
 * </p>
 *
 * @author qinzy7@163.com
 * @since 2019-10-17
 */
@Service
public class TMenuServiceImpl extends ServiceImpl<TMenuMapper, TMenu> implements TMenuService {

    @Autowired
    private TMenuMapper tMenuMapper;

    @Autowired
    private TRoleMapper tRoleMapper;

    /**
     * 查询菜单树
     * @param parentId 0
     * @return 菜单树
     */
    @Override
    public List<TMenu> getByParentId(Integer parentId) {
        List<TMenu> list = tMenuMapper.selectList(new QueryWrapper<TMenu>()
                .select("id, name, parent_id, url, sort").lambda()
                .eq(TMenu::getParentId, parentId)
                .orderByAsc(TMenu::getSort));
        listTree(list);
        return list;
    }

    private void listTree(List<TMenu> list) {
        for (TMenu tMenu:list) {
            List<TMenu> tMenus = tMenuMapper.selectList(new QueryWrapper<TMenu>()
                    .select("id, name, parent_id, url, sort").lambda()
                    .eq(TMenu::getParentId, tMenu.getId())
                    .orderByAsc(TMenu::getSort));
            // 如果存在3级菜单，把以下放开即可，不存在3级菜单就注释掉，不然影响效率
//            listTree(tMenus);
            tMenu.setChildren(tMenus);
        }
    }

    /**
     * 根据菜单id删除菜单，子菜单，以及菜单关联角色
     * @param id 菜单id
     * @return true：成功， false：失败
     */
    @Override
    @Transactional
    public boolean delete(Integer id) {
        List<TMenu> tMenuList = tMenuMapper.selectList(new QueryWrapper<>(new TMenu().setParentId(id)));
        List<Integer> ids = tMenuList.stream().map(TMenu::getId).collect(Collectors.toList());
        ids.add(id);
        tMenuMapper.deleteBatchIds(ids);
        tRoleMapper.deleteRoleMenuByMenuIds(ids);
        return true;
    }
}
