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
 * @since 2023-01-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_books_evaluate")
public class BooksEvaluate extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField("bid")
    private Long bid;

    @TableField("uid")
    private Long uid;

    @TableField("star")
    private Integer star;

    @TableField("comment")
    private String comment;

    @TableField(exist = false)
    private String username;

    @TableField(exist = false)
    private String avatar;

    @TableField(exist = false)
    private String cover;

    @TableField(exist = false)
    private String bookName;



}
