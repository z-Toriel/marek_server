package com.marek.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.marek.common.BaseController;
import com.marek.common.R;
import com.marek.common.ResultCode;
import com.marek.entity.Fans;
import com.marek.service.FansService;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.marek.utils.MD5Utils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Random;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Byterain
 * @since 2022-12-08
 */
@CrossOrigin
@RestController
@RequestMapping("/fans")
public class FansController extends BaseController {
    Integer checkCode;

    @PostMapping("/login")
    public R login(@RequestBody Fans fans){
        Fans fansobj = fansService.getById(fans.getId());
        if(fansobj!=null){
            if (fansobj.getStatu()==0){
                return R.error(ResultCode.USER_ACCOUNT_FORBIDDEN,"账号被禁用");
            }
            //输入的密码相对比工具类
            if(fansobj.getPassword().equals(MD5Utils.md5(MD5Utils.inputTokenPass(fans.getPassword())))){
                fansobj.setToken(jwtUtils.generateToken(fansobj.getId()+""));
                return R.ok().data("fans",fansobj);
            }else {
                return R.error(ResultCode.USER_ACCOUNT_ERROR,"密码错误");
            }
        }else {
            return R.error(ResultCode.USER_NOT_EXIST,"用户不存在");
        }
    }


    @PostMapping("/register")
    public R regist(@RequestBody Fans fans) {
        fans.setPassword(MD5Utils.md5(MD5Utils.inputTokenPass(fans.getPassword())));    //将用户密码加密
        fans.setStatu(1);
        fans.setCreated(LocalDateTime.now());
        fans.setUpdated(LocalDateTime.now());
        fans.setSex(1);
        fans.setDeltag(1);
        fans.setRemainBorrowNumber(3);  // 设置默认借阅数量
        fans.setAvatar("http://markfang1.oss-cn-hangzhou.aliyuncs.com/2022/12/09/ae75c6ddb60c4dd9906b0ac8b46976104.jpg");
        fansService.save(fans); // 存入数据库
        return R.ok().data("fans", fans);
    }

    @GetMapping("/check/{id}")
    public R check(@PathVariable Long id) {
        Fans fans = fansService.getById(id);
        if (fans != null) {
            return R.error(ResultCode.USER_HAS_EXIST, "手机号已存在");
        } else {
            return R.ok();
        }
    }

    @GetMapping("/checkOldPass/")
    public R checkOldPass(String oldPassword, Long fansId) {
        Fans fansobj = fansService.getById(fansId);
        if (fansobj != null) {
            if (!(fansobj.getPassword().equals(MD5Utils.md5(MD5Utils.inputTokenPass(oldPassword))))) {
                return R.error(ResultCode.DATA_NOT_FOUND,"旧密码错误");
            }else{
                return R.ok();
            }
        } else {
            return R.error(ResultCode.USER_NOT_EXIST, "用户不存在");
        }
    }

    @PostMapping("/repass")
    public R repass(@RequestBody Fans fans){
        if (fans.getPassword().equals("")){
            return R.error(ResultCode.DATA_IS_WRONG,"请输入密码");
        }
        fans.setPassword(MD5Utils.md5(MD5Utils.inputTokenPass(fans.getPassword())));    //将用户密码加密
        fansService.updateById(fans);
        return R.ok();
    }

    @GetMapping("/getCheckCode/{userEmail}")
    public R getCheckCode(@PathVariable String userEmail) {
        Random random = new Random();
        this.checkCode = random.nextInt(8999)+1000;
        HtmlEmail email = new HtmlEmail();  // 创建一个htmlEmail对象
        email.setHostName("smtp.163.com"); //设置邮箱的smtp服务器 q
        email.setCharset("utf-8");  // 设置发送的字符类型
        try {
            email.addTo(userEmail); //设置收件人
            email.setFrom("zhu1661908854@163.com","Marek图书馆"); // 设置发件人
            email.setAuthentication("zhu1661908854@163.com","VMEIGEOUDLLWJFVG");
            email.setSubject("欢迎使用marek图书馆用户");
            email.setMsg("这是你的验证码："+this.checkCode);
            email.send(); //发送
            return R.ok().data("checkCode",this.checkCode);
        } catch (EmailException e) {
            e.printStackTrace();
            return R.error(ResultCode.EMAIL_ERROR,"验证码发送失败，请输入正确邮箱");
        }
    }

    //更新用户
    @PostMapping("/update")
    public R update(@RequestBody Fans fans){
        //更新用户的更新时间
        fans.setUpdated(LocalDateTime.now());
        boolean b = fansService.update(fans, new QueryWrapper<Fans>().eq("id", fans.getId()));
        if (b){
            return R.ok();
        }else {
            return R.error(ResultCode.DATA_IS_WRONG,"修改失败");
        }

    }
}
