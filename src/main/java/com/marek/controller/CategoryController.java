package com.marek.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.marek.common.BaseController;
import com.marek.common.R;
import com.marek.common.ResultCode;
import com.marek.entity.Category;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Byterain
 * @since 2022-12-08
 */
@RestController
@CrossOrigin
@RequestMapping("/category")
public class CategoryController extends BaseController {
    //获得所有的分类信息
    @GetMapping("/list")
    public R list() {
        List<Category> list= categoryService.list();
        if (list == null){
            return R.error(ResultCode.DATA_NOT_FOUND,"分类信息数据不存在");
        }else {
            return R.ok().data("categorylist",list);
        }
    }
}
