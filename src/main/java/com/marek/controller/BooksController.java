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

import java.util.List;

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

    // 根据书籍分类获取对应的书籍列表
    @GetMapping("/listByCategory/{categoryId}")
    public R listByCategory(@PathVariable Long categoryId){
        List<Books> bookListByCategory = booksService.list(new QueryWrapper<Books>().eq("category_id", categoryId));
        if (bookListByCategory.size()>0){
            return R.ok().data("list",bookListByCategory);
        }else {
            return R.error(ResultCode.DATA_NOT_FOUND,"数据未找到");
        }
    }

    // 根据作者查找对应的书籍信息
    @GetMapping("/listByauthor")
    public R listByauthor(String authorName){
        List<Books> booksListByAuthor = booksService.list(new QueryWrapper<Books>().like(!authorName.equals(""), "author", authorName));
        if (booksListByAuthor.size()>0){
            return R.ok().data("list",booksListByAuthor);
        }else {
            return R.error(ResultCode.DATA_NOT_FOUND,"数据未找到");
        }
    }

}
