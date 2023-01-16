package com.marek.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.marek.common.R;
import com.marek.common.ResultCode;
import com.marek.entity.Books;
import com.marek.entity.BooksEvaluate;
import com.marek.entity.Borrow;
import com.marek.entity.Fans;
import org.springframework.web.bind.annotation.*;

import com.marek.common.BaseController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Byterain
 * @since 2023-01-12
 */
@RestController
@CrossOrigin
@RequestMapping("/evaluate")
public class BooksEvaluateController extends BaseController {

    @GetMapping("/infoUser/{bid}")
    public R getUserEvalueate(@PathVariable Long bid){
        List<BooksEvaluate> evas = booksEvaluateService.list(new QueryWrapper<BooksEvaluate>()
                .eq("bid", bid)
                .last("order by created desc")
        );

        for (BooksEvaluate eva : evas){
            Long uid = eva.getUid();    // 通过评论获取到uid
            Fans fansById = fansService.getById(uid);  // 获取fans对象
            String username = fansById.getUsername();
            String avatar = fansById.getAvatar();
            eva.setUsername(username);
            eva.setAvatar(avatar);
        }
        return R.ok().data("evaluates",evas);
    }

    //保存评论
    @GetMapping("/save")
    public R save (Long uid,Long bid,String comment,Integer star){
        System.out.println("=========="+uid+"-----------"+star);
        BooksEvaluate booksEvaluate = new BooksEvaluate();
        booksEvaluate.setUid(uid);
        booksEvaluate.setBid(bid);
        booksEvaluate.setComment(comment);
        booksEvaluate.setStar(star);
        booksEvaluate.setStatu(1);
        booksEvaluate.setCreated(LocalDateTime.now());
        booksEvaluate.setUpdated(LocalDateTime.now());
        boolean b = booksEvaluateService.save(booksEvaluate);

        if(b){
            return R.ok();
        }else {
            return R.error(ResultCode.DATA_NOT_FOUND,"没有数据");
        }
    }

    @GetMapping("/num/{uid}")
    public R num(@PathVariable Long uid){
        Integer count = booksEvaluateService.count(new QueryWrapper<BooksEvaluate>().eq("uid", uid));
        if (count != null){
            return R.ok().data("booksEvaluateNum",count);
        }else {
            return R.error(ResultCode.DATA_NOT_FOUND,"数据未找到");
        }
    }

    // 获取登录用户的所有评论
    @GetMapping("/list/{uid}")
    public R list(@PathVariable Long uid){
        List<BooksEvaluate> booksEvaluateList = booksEvaluateService.list(new QueryWrapper<BooksEvaluate>().eq("uid", uid));
        if (booksEvaluateList!=null){
            booksEvaluateList.forEach(item->{
                Fans fans = fansService.getById(uid);
                String username = fans.getUsername();
                item.setUsername(username);

                Long bid = item.getBid();
                Books book = booksService.getById(bid);
                String bookName = book.getName();
                String cover = book.getCover();
                item.setCover(cover);
                item.setBookName(bookName);
            });
            return R.ok().data("booksEvaluateList",booksEvaluateList);
        }else {
            return R.error(ResultCode.DATA_NOT_FOUND,"数据未找到");
        }
    }

    // 删除评论
    @PostMapping("/delEvaluate/{id}")
    public R delEvaluate(@PathVariable Long id){
        boolean b = booksEvaluateService.removeById(id);
        if (b){
            return R.ok();
        }else{
            return R.error(ResultCode.DATA_NOT_FOUND,"未能删除");
        }
    }


}
