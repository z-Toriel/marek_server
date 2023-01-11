package com.marek.common;

import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ToString
public class R {
    private Integer code;
    private boolean success;
    private String message;

    private Map<String,Object> data = new HashMap<>();

    public static R ok(){
        R r = new R();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("请求成功");
        return r;
    }

    public static R error(Integer code,String message){
        R r = new R();
        r.setSuccess(false);
        r.setCode(code);
        r.setMessage(message);
        return r;
    }

    public R data(String key,Object value){
        this.data.put(key,value);
        return this;
    }
}
