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
 * @since 2023-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_books")
public class Books extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField("name")
    private String name;

    @TableField("author")
    private String author;

    @TableField("category_id")
    private Long categoryId;

    @TableField(exist = false)
    private String categoryName;

    @TableField("region")
    private String region;

    @TableField("remain")
    private Integer remain;

    @TableField("cover")
    private String cover;

    /**
     * 出版社
     */
    @TableField("press")
    private String press;

    /**
     * 数量
     */
    @TableField("total")
    private Integer total;

    @TableField("is_delete")
    private Integer isDelete;

    @TableField("introduce")
    private String introduce;

    // 位置
    @TableField(exist = false)
    private String location;




}
