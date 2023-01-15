package com.marek.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.marek.common.R;
import com.marek.entity.Poster;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.marek.common.BaseController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Byterain
 * @since 2023-01-15
 */
@RestController
@CrossOrigin
@RequestMapping("/poster")
public class PosterController extends BaseController {
    @GetMapping("list")
    public R getAllBanner(){

        List<Poster> posterList =posterService.list(new QueryWrapper<Poster>().eq("statu",1));
        return R.ok().data("posterList",posterList);
    }
}
