package com.marek.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Utils {

    public static String md5(String str){
        return DigestUtils.md5Hex(str);
    }

    // 加密字符 基数
    private static final String privateKey = "1xc2d34f3p";

    // 123123，密码加工
    public static String inputTokenPass(String pass){
        String newPass = privateKey.charAt(0)+privateKey.charAt(1)+pass+privateKey.charAt(5)+"";

        return newPass;
    }


}
