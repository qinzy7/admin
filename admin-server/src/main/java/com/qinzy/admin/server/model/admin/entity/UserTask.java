package com.qinzy.admin.server.model.admin.entity;

    import com.baomidou.mybatisplus.annotation.TableName;
    import com.baomidou.mybatisplus.extension.activerecord.Model;
    import java.util.Date;
    import java.io.Serializable;
    import io.swagger.annotations.ApiModel;
    import io.swagger.annotations.ApiModelProperty;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* <p>
    * 用户完成任务纪录表
    * </p>
*
* @author qinzy7@163.com
* @since 2019-11-07
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("ssb_user_task")
    @ApiModel(value="UserTask对象", description="用户完成任务纪录表")
    public class UserTask extends Model<UserTask> {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "主键ID")
    private String id;

            @ApiModelProperty(value = "用户ID")
    private String userId;

            @ApiModelProperty(value = "任务ID")
    private String taskId;

            @ApiModelProperty(value = "任务奖励")
    private Integer num;

            @ApiModelProperty(value = "累计访问天数")
    private Integer days;

            @ApiModelProperty(value = "创建时间")
    private Date createDate;

            @ApiModelProperty(value = "创建者")
    private String createUser;

            @ApiModelProperty(value = "最后更新时间")
    private Date lastUpdateDate;

            @ApiModelProperty(value = "最后更新者")
    private String lastUpdateUser;

            @ApiModelProperty(value = "删除标识：0未删除  1删除")
    private Integer deleteFlag;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
