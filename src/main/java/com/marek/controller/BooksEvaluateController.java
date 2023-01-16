package com.marek.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.marek.common.R;
import com.marek.common.ResultCode;
import com.marek.entity.BooksEvaluate;
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
        booksEvaluate.setCreated(LocalDateTime.now());
        booksEvaluate.setUpdated(LocalDateTime.now());
        boolean b = booksEvaluateService.save(booksEvaluate);

        if(b){
            return R.ok();
        }else {
            return R.error(ResultCode.DATA_NOT_FOUND,"没有数据");
        }
    }


}
