package com.qinzy.admin.server.model.admin.service.impl;

import com.qinzy.admin.server.model.admin.entity.LogEntity;
import com.qinzy.admin.server.model.admin.mapper.LogMapper;
import com.qinzy.admin.server.model.admin.service.LogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 操作日志表 服务实现类
 * </p>
 *
 * @author qinzy7@163.com
 * @since 2019-11-06
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, LogEntity> implements LogService {

}
