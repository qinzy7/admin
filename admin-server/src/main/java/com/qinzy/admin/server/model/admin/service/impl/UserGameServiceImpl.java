package com.qinzy.admin.server.model.admin.service.impl;

import com.qinzy.admin.server.model.admin.entity.UserGame;
import com.qinzy.admin.server.model.admin.mapper.UserGameMapper;
import com.qinzy.admin.server.model.admin.service.UserGameService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户访问游戏表 服务实现类
 * </p>
 *
 * @author qinzy7@163.com
 * @since 2019-11-07
 */
@Service
public class UserGameServiceImpl extends ServiceImpl<UserGameMapper, UserGame> implements UserGameService {

}
