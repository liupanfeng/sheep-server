package com.demo.sheep.constant;

public enum UserApplyStateEnum {
    COMMENT_TO_AUDIT(0,"提交审核"),
    AUDIT_PASS(1,"审核通过"),
    AUDIT_REFUSE(2,"审核拒绝"),
    CANCEL(3,"取消");
    private int code;
    private String desc;
    UserApplyStateEnum(int code, String desc){
        this.code = code;
        this.desc = desc;
    }
    public int code(){
        return this.code;
    }
}
