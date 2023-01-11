package com.marek.intercepter;


import cn.hutool.json.JSONUtil;
import com.marek.common.R;
import com.marek.common.ResultCode;
import com.marek.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

@Slf4j
public class TokenIntercepter implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        JwtUtils jwtUtils = new JwtUtils();

        // 如果Vue的请求设置，请求头变为跨域，正式请求之前发送一次嗅探请求options请求，然后才是正式的请求
        OutputStream out = response.getOutputStream();
        if(request.getMethod().toUpperCase().equals("OPTIONS")){
            response.setHeader("Access-Control-Allow-Origin","*");
            response.setHeader("Access-Control-Allow-Headers","*");
            response.setHeader("Access-Control-Allow-Methods","*");
            response.setHeader("Access-Control-Allow-Credentials","true");
            response.setHeader("Access-Control-Max-Age","3600");
            return true;
        }

        // 获得token
        String token = request.getHeader("token");
        log.info("token拦截获得的值------"+token);
        if(token == null){
            out.write(JSONUtil.toJsonStr(R.error(ResultCode.TOKEN_NOT_EXIST,"token不存在，不能访问")).getBytes("utf-8"));

            out.flush();
            out.close();
            return false;
        }
        if(StringUtils.isNotBlank(token)){
            Claims claims = jwtUtils.getClaimByToken(token);
            if(claims == null){
                out.write(JSONUtil.toJsonStr(R.error(ResultCode.TOKEN_ERROR,"token错误，无权访问")).getBytes("utf-8"));
                out.flush();
                out.close();
                return false;
            }else{
                return true;
            }
        }

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
