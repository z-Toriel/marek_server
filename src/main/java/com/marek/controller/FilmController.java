package com.marek.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.marek.common.BaseController;
import com.marek.common.R;
import com.marek.entity.Film;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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
@RequestMapping("/film")
public class FilmController extends BaseController {
    // 显示电影信息首页：
    // 参数一：type类型（0显示全部电影，1显示热映，2显示即将上映）
    // 参数二：name搜索的名字
    @GetMapping("/list/{type}")
    public R list(@PathVariable Integer type,String filmName){
        // 判断Type==1，查询热映
        if(type == 1){
            Page<Film> filmPage = filmService.page(getPage(), new QueryWrapper<Film>().le("release_time", LocalDateTime.now()).like(StrUtil.isNotBlank(filmName),"name",filmName));
        }
        // 分页查询，gt表示>   如果type=2则执行gt语句,如果有filmNmae则查询like语句,第二个参数表示数据库里的列明，第三个参数表示查询的条件.
        Page<Film> filmPage = filmService.page(getPage(), new QueryWrapper<Film>().gt(type == 2, "release_time", LocalDateTime.now()).like(StrUtil.isNotBlank(filmName), "name", filmName));
        return R.ok().data("films",filmPage);
    }
}
