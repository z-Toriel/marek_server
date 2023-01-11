package com.marek.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.marek.common.R;
import com.marek.entity.Books;
import com.marek.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.marek.common.BaseController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Byterain
 * @since 2023-01-07
 */
@RestController
@CrossOrigin
@RequestMapping("/books")
public class BooksController extends BaseController {
    @Autowired
    BooksService booksService;

    @GetMapping("list")
    public R list(String name){
        Page booksList = booksService.page(getPage(), new QueryWrapper<Books>().like(StrUtil.isNotBlank(name), "name", name));

        return R.ok().data("booksList",booksList);
    }

}
