package com.marek.entity;

import com.marek.common.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
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
 * @since 2023-01-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_borrow")
public class Borrow extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableField("uid")
    private Long uid;

    /**
     * 书籍id
     */
    @TableField("bid")
    private Long bid;

    /**
     * 借阅日期
     */
    @TableField("borrow_date")
    private LocalDate borrowDate;

    /**
     * 预计还书日期
     */
    @TableField("return_date")
    private LocalDate returnDate;

    /**
     * 实际还书日期
     */
    @TableField("real_return_date")
    private LocalDate realReturnDate;

    /**
     * 赔偿金额
     */
    @TableField("compensation")
    private Integer compensation;


    // 书名
    @TableField(exist = false)
    private String bookName;

    // 作者
    @TableField(exist = false)
    private String author;

    // 图书封面
    @TableField(exist = false)
    private String cover;


}
