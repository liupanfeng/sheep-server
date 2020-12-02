package com.demo.sheep.constant;

public enum  CommonStateEnum {
	YES(1,"是"),
	NO(0,"否"),
	NORMAL(1,"正常"),
	DELETE(-1,"删除");
	private Integer code;
	private String desc;
	CommonStateEnum(Integer code, String desc){
		this.code = code;
		this.desc = desc;
	}
	public Integer code(){
		return this.code;
	}
}
