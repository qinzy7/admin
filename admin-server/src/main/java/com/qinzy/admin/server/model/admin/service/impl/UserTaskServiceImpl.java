package com.qinzy.admin.server.model.admin.service.impl;

import com.qinzy.admin.server.model.admin.entity.UserTask;
import com.qinzy.admin.server.model.admin.mapper.UserTaskMapper;
import com.qinzy.admin.server.model.admin.service.UserTaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户完成任务纪录表 服务实现类
 * </p>
 *
 * @author qinzy7@163.com
 * @since 2019-11-07
 */
@Service
public class UserTaskServiceImpl extends ServiceImpl<UserTaskMapper, UserTask> implements UserTaskService {

}
