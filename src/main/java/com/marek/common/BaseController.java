package com.marek.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.marek.service.FansService;
import com.marek.service.FilmService;
import com.marek.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.http.HttpServletRequest;


public class BaseController {
    @Autowired
    public HttpServletRequest request;

    @Autowired
    public FansService fansService;

    @Autowired
    public JwtUtils jwtUtils;

    @Autowired
    public FilmService filmService;

    //获得分页数据  前端请求传递参数current size  得到参数 封装为一个page对象（MP分页使用）。
    public Page getPage(){
        int current = ServletRequestUtils.getIntParameter(request, "current", 1);//得到分页数据的当前页码
        int size = ServletRequestUtils.getIntParameter(request, "size", 5);//得到一页的记录数
        return new Page(current,size);
    }
}
