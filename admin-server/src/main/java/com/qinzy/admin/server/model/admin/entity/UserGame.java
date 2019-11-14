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
    * 用户访问游戏表
    * </p>
*
* @author qinzy7@163.com
* @since 2019-11-07
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("ssb_user_game")
    @ApiModel(value="UserGame对象", description="用户访问游戏表")
    public class UserGame extends Model<UserGame> {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "主键ID")
    private String id;

            @ApiModelProperty(value = "游戏ID")
    private String gameId;

            @ApiModelProperty(value = "用户id")
    private String userId;

            @ApiModelProperty(value = "访问时间")
    private Date createDate;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
