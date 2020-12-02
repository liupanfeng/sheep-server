package com.demo.sheep.utils;

public class RedisUtil {
    private static final String SEPARATOR = ":";//分隔符
    private static final String USER = "USER";
    private static final String TOKEN = "TOKEN";
    private static final String TABLE = "TABLE";//database object
    private static final String VERIFY_CODE = "VERIFY_CODE";//验证码

    private static String concat(String ...params){
        return String.join(SEPARATOR, params);
    }

    /**
     *  所有用户的map集合
     */
    public static String userKeyOfHash(){
        return concat(USER, TABLE);
    }

    /**
     *  每个token对应的用户
     */
    public static String tokenKeyOfString(String token){
        return concat(USER,TOKEN,token);
    }

    /**
     *  verificationCode
     */
    public static String verifyCodeKeyOfString(String mode,String account){
        return concat(USER,VERIFY_CODE,mode,account);
    }


}
