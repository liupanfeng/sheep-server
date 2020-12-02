package com.demo.sheep.constant;

public enum AuthStateEnum {
	PASS(1,"审核通过"),
	REFUSE(-1,"拒绝"),
	WAIT(0,"待审核");
	private Integer code;
	private String desc;
	AuthStateEnum(Integer code, String desc){
		this.code = code;
		this.desc = desc;
	}
	public Integer code(){
		return this.code;
	}

	public static AuthStateEnum check(Integer code){
		for (AuthStateEnum modeEnum : AuthStateEnum.values()) {
			if(modeEnum.code().equals(code)){
				return modeEnum;
			}
		}
		return null;
	}}
