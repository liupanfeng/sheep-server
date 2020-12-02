package com.demo.sheep.constant;

public enum UploadModeEnum {
    USER_ICON(1,"user_icon/%s/%s/"),
    USER_ID_CARD_FRONT(2,"user_front_card/%s/%s/"),
    USER_ID_CARD_REVERSE(3,"user_reverse_card/%s/%s/"),
    MARKET(4,"market/%s/%s/"),
    SERVICE(5,"service/%s/%s/"),
    BANNER(6,"banner/%s/%s/"),
    NEWS(7,"news/%s/%s/");

    private Integer code;
    private String path;
    UploadModeEnum(Integer code, String path){
        this.code = code;
        this.path = path;
    }
    public Integer code(){
        return this.code;
    }
    public String path(){
        return this.path;
    }

    public static UploadModeEnum check(Integer code){
        for (UploadModeEnum modeEnum : UploadModeEnum.values()) {
            if(modeEnum.code().equals(code)){
                return modeEnum;
            }
        }
        return null;
    }
}
