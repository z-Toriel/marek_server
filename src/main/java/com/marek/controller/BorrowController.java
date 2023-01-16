package com.marek.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.marek.common.R;
import com.marek.common.ResultCode;
import com.marek.entity.Borrow;
import com.marek.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.marek.common.BaseController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Byterain
 * @since 2023-01-16
 */
@RestController
@CrossOrigin
@RequestMapping("/borrow")
public class BorrowController extends BaseController {
    @Autowired
    BorrowService borrowService;

    @GetMapping("/num/{uid}")
    public R num(@PathVariable Long uid){
        Integer count = borrowService.count(new QueryWrapper<Borrow>().eq("uid", uid));
        if (count != null){
            return R.ok().data("borrowNum",count);
        }else {
            return R.error(ResultCode.DATA_NOT_FOUND,"数据未找到");
        }
    }

    @GetMapping("/returnBooksNum/{uid}")
    public R returnBooksNum(@PathVariable Long uid){
        Integer count = borrowService.count(new QueryWrapper<Borrow>()
                .eq("uid", uid)
                .eq("statu", 0)
        );

        if (count != null){
            return R.ok().data("returnBooksNum",count);
        }else {
            return R.error(ResultCode.DATA_NOT_FOUND,"数据未找到");
        }
    }
}
