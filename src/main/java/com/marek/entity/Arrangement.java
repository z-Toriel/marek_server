package com.marek.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDate;
import java.time.LocalTime;

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
@TableName("sys_arrangement")
public class Arrangement extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField("fid")
    private Long fid;

    @TableField("name")
    private String name;

    @TableField("seat_number")
    private Integer seatNumber;

    @TableField("box_office")
    private Integer boxOffice;

    @TableField("price")
    private BigDecimal price;

    @TableField("type")
    private String type;

    @TableField("date")
    private LocalDate date;

    @TableField("start_time")
    private LocalTime startTime;

    @TableField("end_time")
    private LocalTime endTime;


}
