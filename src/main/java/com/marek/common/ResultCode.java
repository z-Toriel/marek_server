package com.marek.common;
//返回状态码
public class ResultCode {
    //成功状态码
    public static final Integer SUCCESS=1;
    //参数错误
    public static final Integer USER_NOT_EXIST=20001;  //用户不存在
    public static final Integer USER_NOT_LOGGED_ID=20002;//用户未登录
    public static final Integer USER_ACCOUNT_ERROR=20003;//密码错误
    public static final Integer USER_ACCOUNT_FORBIDDEN=20004;//用户账号被禁用
    public static final Integer USER_HAS_EXIST=20005;    //用户已存在
    public static final Integer USER_HAS_EXPIRED=20006;//用户过期，token过期

    //token验证
    public static final Integer TOKEN_NOT_EXIST=30001;//用户过期，token过期
    public static final Integer TOKEN_ERROR=30002;//用户过期，token过期


    //数据查询的错误码
    public static final Integer DATA_NOT_FOUND = 50001; //数据没有查询到
    public static final Integer DATA_IS_WRONG = 50002; //数据查询有误
    public static final Integer DATA_ALREADY_EXTISTED = 50003; //数据已经存在


    // 邮箱发送错误
    public static final Integer EMAIL_ERROR=60001;


}
