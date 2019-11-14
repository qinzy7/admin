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
    * 用户中奖纪录表
    * </p>
*
* @author qinzy7@163.com
* @since 2019-11-07
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("ssb_user_winning_record")
    @ApiModel(value="UserWinningRecord对象", description="用户中奖纪录表")
    public class UserWinningRecord extends Model<UserWinningRecord> {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "主键ID")
    private String id;

            @ApiModelProperty(value = "游戏ID")
    private String gameId;

            @ApiModelProperty(value = "用户id")
    private String userId;

            @ApiModelProperty(value = "奖品名称")
    private String prizeId;

            @ApiModelProperty(value = "中奖时间")
    private Date winningDate;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
