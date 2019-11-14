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
    * 拾尚包游戏表
    * </p>
*
* @author qinzy7@163.com
* @since 2019-11-07
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("ssb_games")
    @ApiModel(value="Games对象", description="拾尚包游戏表")
    public class Games extends Model<Games> {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "主键ID")
    private String id;

            @ApiModelProperty(value = "游戏名称")
    private String gameName;

            @ApiModelProperty(value = "备注")
    private String gameDesc;

            @ApiModelProperty(value = "开始时间")
    private Date startDate;

            @ApiModelProperty(value = "结束时间")
    private Date endDate;

            @ApiModelProperty(value = "状态：1启用，0禁用")
    private Integer state;

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
