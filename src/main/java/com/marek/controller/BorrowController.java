package com.marek.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.marek.common.R;
import com.marek.common.ResultCode;
import com.marek.entity.Books;
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

    @GetMapping("/list/{uid}")
    public R list(@PathVariable Long uid){
        // 1. 获取对象
        List<Borrow> borrowsList0 = borrowService.list(new QueryWrapper<Borrow>()
                .eq("uid", uid)
                .eq("statu", 0)
        );

        List<Borrow> borrowsList1 = borrowService.list(new QueryWrapper<Borrow>()
                .eq("uid", uid)
                .eq("statu", 1)
        );

        List<Borrow> borrowsList2 = borrowService.list(new QueryWrapper<Borrow>()
                .eq("uid", uid)
                .eq("statu", 2)
        );

        List<Borrow> borrowsList3 = borrowService.list(new QueryWrapper<Borrow>()
                .eq("uid", uid)
                .eq("statu", 3)
        );

        List<Borrow> borrowsList4 = borrowService.list(new QueryWrapper<Borrow>()
                .eq("uid", uid)
                .eq("statu", 4)
        );

        if (borrowsList0!=null && borrowsList1!=null){
            borrowsList0.forEach(item->{
                Long bid = item.getBid();
                Books booksById = booksService.getById(bid);
                String author = booksById.getAuthor();
                String name = booksById.getName();
                String cover = booksById.getCover();
                item.setAuthor(author);
                item.setBookName(name);
                item.setCover(cover);
            });

            borrowsList1.forEach(item->{
                Long bid = item.getBid();
                Books booksById = booksService.getById(bid);
                String author = booksById.getAuthor();
                String name = booksById.getName();
                String cover = booksById.getCover();
                item.setAuthor(author);
                item.setBookName(name);
                item.setCover(cover);
            });

            borrowsList2.forEach(item->{
                Long bid = item.getBid();
                Books booksById = booksService.getById(bid);
                String author = booksById.getAuthor();
                String name = booksById.getName();
                String cover = booksById.getCover();
                item.setAuthor(author);
                item.setBookName(name);
                item.setCover(cover);
            });

            borrowsList3.forEach(item->{
                Long bid = item.getBid();
                Books booksById = booksService.getById(bid);
                String author = booksById.getAuthor();
                String name = booksById.getName();
                String cover = booksById.getCover();
                item.setAuthor(author);
                item.setBookName(name);
                item.setCover(cover);
            });

            borrowsList4.forEach(item->{
                Long bid = item.getBid();
                Books booksById = booksService.getById(bid);
                String author = booksById.getAuthor();
                String name = booksById.getName();
                String cover = booksById.getCover();
                item.setAuthor(author);
                item.setBookName(name);
                item.setCover(cover);
            });
            return R.ok().data("borrowsList0",borrowsList0)
                    .data("borrowsList1",borrowsList1)
                    .data("borrowsList2",borrowsList2)
                    .data("borrowsList3",borrowsList3)
                    .data("borrowsList4",borrowsList4);
        }else{
            return R.error(ResultCode.DATA_NOT_FOUND,"数据未找到");
        }

    }

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
