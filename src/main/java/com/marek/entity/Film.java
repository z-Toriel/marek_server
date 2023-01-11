package com.marek.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDate;

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
@TableName("sys_film")
public class Film extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField("name")
    private String name;

    @TableField("release_time")
    private LocalDate releaseTime;

    @TableField("category_id")
    private Long categoryId;

    @TableField("region")
    private String region;

    @TableField("cover")
    private String cover;

    @TableField("duration")
    private Integer duration;

    @TableField("grade")
    private Integer grade;


}
