package com.qinzy.admin.server.model.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 操作日志表
 * </p>
 *
 * @author qinzy7@163.com
 * @since 2019-11-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_log")
@ApiModel(value = "Log对象", description = "操作日志表")
public class LogEntity extends Model<LogEntity> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "菜单")
    private String menu;

    @ApiModelProperty(value = "动作")
    private String action;

    @ApiModelProperty(value = "操作")
    private String operation;

    @ApiModelProperty(value = "请求ip")
    private String requestIp;

    @ApiModelProperty(value = "请求用户id")
    private Integer requestUserId;

    @ApiModelProperty(value = "请求时间")
    private Date requestDate;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
