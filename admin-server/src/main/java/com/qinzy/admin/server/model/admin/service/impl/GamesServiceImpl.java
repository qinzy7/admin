package com.qinzy.admin.server.model.admin.service.impl;

import com.qinzy.admin.server.model.admin.entity.Games;
import com.qinzy.admin.server.model.admin.mapper.GamesMapper;
import com.qinzy.admin.server.model.admin.service.GamesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 拾尚包游戏表 服务实现类
 * </p>
 *
 * @author qinzy7@163.com
 * @since 2019-11-07
 */
@Service
public class GamesServiceImpl extends ServiceImpl<GamesMapper, Games> implements GamesService {

}
