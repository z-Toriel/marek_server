package com.marek.entity;

import com.marek.common.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Byterain
 * @since 2023-01-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_poster")
public class Poster extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField("title")
    private String title;

    @TableField("url")
    private String url;


}
