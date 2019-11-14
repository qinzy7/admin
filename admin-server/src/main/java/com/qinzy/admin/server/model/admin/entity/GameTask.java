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
    * 游戏任务表
    * </p>
*
* @author qinzy7@163.com
* @since 2019-11-07
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("ssb_game_task")
    @ApiModel(value="GameTask对象", description="游戏任务表")
    public class GameTask extends Model<GameTask> {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "主键ID")
    private String id;

            @ApiModelProperty(value = "游戏ID")
    private String gameId;

            @ApiModelProperty(value = "任务名称")
    private String taskName;

            @ApiModelProperty(value = "任务编码：对应任务图标")
    private String taskCode;

            @ApiModelProperty(value = "任务奖励：翻牌次数+num")
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
