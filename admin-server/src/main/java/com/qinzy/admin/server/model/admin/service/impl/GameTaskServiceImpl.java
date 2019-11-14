package com.qinzy.admin.server.model.admin.service.impl;

import com.qinzy.admin.server.model.admin.entity.GameTask;
import com.qinzy.admin.server.model.admin.mapper.GameTaskMapper;
import com.qinzy.admin.server.model.admin.service.GameTaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 游戏任务表 服务实现类
 * </p>
 *
 * @author qinzy7@163.com
 * @since 2019-11-07
 */
@Service
public class GameTaskServiceImpl extends ServiceImpl<GameTaskMapper, GameTask> implements GameTaskService {

}
