package com.marek.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.marek.common.R;
import com.marek.common.ResultCode;
import com.marek.entity.Books;
import com.marek.entity.Category;
import com.marek.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    //通过id获得书籍的详情信息
    @GetMapping("/info/{id}")
    public R info (@PathVariable Long id){
        Books book = booksService.getById(id);

        Long categoryId = book.getCategoryId();
        Category categoryById = categoryService.getById(categoryId);
        String categoryName = categoryById.getName();
        String location = categoryById.getLocation();
        book.setCategoryName(categoryName);
        book.setLocation(location);


        if (book == null){
            return R.error(ResultCode.DATA_NOT_FOUND,"电影信息数据不存在");
        }else {
            return R.ok().data("book",book);
        }
    }

}
