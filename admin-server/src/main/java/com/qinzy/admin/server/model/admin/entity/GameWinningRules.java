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
    * 游戏中奖规则表
    * </p>
*
* @author qinzy7@163.com
* @since 2019-11-07
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("ssb_game_winning_rules")
    @ApiModel(value="GameWinningRules对象", description="游戏中奖规则表")
    public class GameWinningRules extends Model<GameWinningRules> {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "主键ID")
    private String id;

            @ApiModelProperty(value = "游戏ID")
    private String gameId;

            @ApiModelProperty(value = "规则名称")
    private String ruleName;

            @ApiModelProperty(value = "规则编码：编码对应奖品切图")
    private String ruleCode;

            @ApiModelProperty(value = "规则类型：1实物，2红包")
    private Integer ruleType;

            @ApiModelProperty(value = "中奖概率：以百分比形式")
    private String winningOdds;

            @ApiModelProperty(value = "奖品ID")
    private String prizeId;

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
