package com.marek.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.marek.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Byterain
 * @since 2022-12-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_fans")
public class Fans extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField("id")
    public Long id;

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("avatar")
    private String avatar;

    @TableField("email")
    private String email;

    @TableField("sex")
    private Integer sex;

    @TableField("info")
    private String info;

    @TableField("delTag")
    private Integer deltag;

    @TableField(exist = false)
    private String token;


}
