package com.qinzy.admin.server.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Set;

/**
 * <p>
 * 后台角色表
 * </p>
 *
 * @author qinzy7@163.com
 * @since 2019-10-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_role")
@ApiModel(value = "TRole对象", description = "后台角色表")
public class TRole extends Model<TRole> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "角色描述")
    private String desc;

    /**
     * 角色菜单列表
     */
    @TableField(exist = false)
    private Set<Integer> menuIds;

    /**
     * 角色权限列表
     */
    @TableField(exist = false)
    private Set<TPermission> permissions;

    /**
     * 角色用户列表
     */
    @TableField(exist = false)
    private Set<TUser> users;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
