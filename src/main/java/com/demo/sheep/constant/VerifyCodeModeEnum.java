package com.demo.sheep.constant;

public enum VerifyCodeModeEnum {
	IMAGE("用来发短信的图片验证码"),
	REGISTER("注册手机号的短信验证码"),
	BIND("绑定手机号的短信验证码");
	private String desc;
	VerifyCodeModeEnum( String desc){
		this.desc = desc;
	}

	public static VerifyCodeModeEnum check(String code){
		for (VerifyCodeModeEnum modeEnum : VerifyCodeModeEnum.values()) {
			if(modeEnum.name().equalsIgnoreCase(code)){
				return modeEnum;
			}
		}
		return null;
	}
}
