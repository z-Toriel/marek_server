package com.marek.common;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseEntity {
    @TableId(value = "id",type= IdType.AUTO)
    public Long id;

    private LocalDateTime created;
    private LocalDateTime updated;
    private Integer statu;
}
