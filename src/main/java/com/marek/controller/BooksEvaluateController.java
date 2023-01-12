package com.marek.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.marek.common.R;
import com.marek.entity.BooksEvaluate;
import com.marek.entity.Fans;
import org.springframework.web.bind.annotation.*;

import com.marek.common.BaseController;

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

//    @GetMapping("/infoUser/{fid}")
//    public R getUserEvalueate(@PathVariable Long fid){
//        //根据uid查询
//        List<FilmEvaluate> evas = filmEvaluateService.list(new QueryWrapper<FilmEvaluate>()
//                .eq("fid",fid)
//                .last("order by created desc")
//        );
//
//        for (FilmEvaluate eva : evas) {
//            if (fansService.getById(eva.getUid())!=null){
//                eva.setUsername(fansService.getById(eva.getUid()).getUsername());
//                eva.setAvatar(fansService.getById(eva.getUid()).getAvatar());
//            }
//        }
//        return R.ok().data("evaluates",evas);
//    }

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
}
