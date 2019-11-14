package com.qinzy.admin.server.model.admin.entity;

    import com.baomidou.mybatisplus.annotation.TableName;
    import com.baomidou.mybatisplus.extension.activerecord.Model;
    import java.io.Serializable;
    import io.swagger.annotations.ApiModel;
    import io.swagger.annotations.ApiModelProperty;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* <p>
    * 用户抽奖次数表
    * </p>
*
* @author qinzy7@163.com
* @since 2019-11-07
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("ssb_user_draw_times")
    @ApiModel(value="UserDrawTimes对象", description="用户抽奖次数表")
    public class UserDrawTimes extends Model<UserDrawTimes> {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "主键ID")
    private String id;

            @ApiModelProperty(value = "用户ID")
    private String userId;

            @ApiModelProperty(value = "游戏ID")
    private String gameId;

            @ApiModelProperty(value = "获取抽奖总次数")
    private Integer totalNum;

            @ApiModelProperty(value = "抽奖剩余次数")
    private Integer residueNum;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
