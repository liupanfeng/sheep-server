package com.demo.sheep.constant;

public enum ResultCode {
    SUCCESS(1,"成功","success"),
    FAILED(-1,"失败","failed"),
    TOKEN_IS_INVALID(-2,"无效的token","failed"),
    /**
     * 参数错误  1001 -- 1999
     */
    PARAM_IS_INVALID(1001,"参数无效","param is invalid"),
    PARAM_IS_BLANK(1002,"参数为空","param is blank"),
    PARAM_TYPE_BIND_ERROR(1003,"参数类型错误","param type bind error"),
    PARAM_NOT_COMPLETE(1004,"缺少参数","param not complete"),
    PARAM_FILE_NAME_IS_BLANK(1005,"文件名不能为空", "param file name is blank"),
    PARAM_FILE_TYPE_NOT_ALLOW(1006,"不被允许的文件格式", "param file type not allow"),
    PARAM_IS_TOO_LONG(1007,"参数值过长", "param is too long"),
    PHONE_IS_INVALID(1008,"手机号格式错误", "phone is invalid"),
    PASSWORD_IS_TOO_LONG(1009,"密码最大长度为20个字符", "password too long"),
    VERIFY_CODE_IS_INVALID(1010,"验证码不正确", "verification code is invalid"),
    EMAIL_IS_INVALID(1011,"邮箱格式错误", "email is invalid"),
    OLD_PASSWORD_IS_ERROR(1012,"原密码错误", "incorrect old password"),
    PARAM_IS_TOO_SHORT(1013,"参数值太短", "param is too short"),
    PHONE_IS_EXISTS(1014,"手机号已经存在", "phone is exists"),
    EMAIL_IS_EXISTS(1015,"邮箱已经存在", "email is exists"),
    OLD_EMAIL_IS_ERROR(1016,"原邮箱错误", "old email is error"),
    OLD_PHONE_IS_ERROR(1017,"原手机号错误", "old phone is error"),
    VERIFY_CODE_FREQUENTLY(1018,"发送验证码太频繁", "verification code is send frequently"),


    /**
     * 用户错误 2001 -- 2999
     */
    USER_NOT_LOGGED_IN(2001,"用户未登录", "user not logged in"),
    USER_LOGIN_ERROR(2002,"账号名或密码错误", "incorrect username or password"),
    USER_NOT_EXISTS(2003,"用户不存在", "user not exists"),
    USER_HAS_EXISTS(2004,"用户已存在", "user has exists"),
    USER_ACCOUNT_FORBIDDEN(2005,"用户已被禁用", "user account forbidden"),
    USER_NOT_ACCOUNT(2006,"当前用户没有权限", "user not account"),
    ENTERPRISE_NOT_AUDIT(2007,"企业尚未认证通过", "enterprise not auth"),
    ENTERPRISE_ALREADY_AUDIT(2008,"企业已经认证通过", "enterprise already auth"),
    /**
     * 接口错误 3001 -- 3999
     */
    UNKNOWN_ERROR(3001,"未知错误", "unknown error"),
    OBJECT_NOT_EXISTS(3002,"对象不存在", "object not exists"),
    NOT_ENOUGH_DURATION(3003,"可用的视频生成时长不够", "not enough duration"),
    APP_NOT_EXISTS(3004,"应用不存在", "app not exists");


    private Integer code;
    private String zh_message;
    private String en_message;

    ResultCode(Integer code, String zh_message, String en_message){
        this.code = code;
        this.zh_message = zh_message;
        this.en_message = en_message;
    }

    public Integer code(){
        return this.code;
    }
    public String enMessage(){
        return this.en_message;
    }
    public String zhMessage(){
        return this.zh_message;
    }


}
