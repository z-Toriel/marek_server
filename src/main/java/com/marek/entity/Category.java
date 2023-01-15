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
@TableName("sys_category")
public class Category extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField("name")
    private String name;

    @TableField("location")
    private String location;

    @TableField(exist = false)
    private Integer booksNumber;


}
