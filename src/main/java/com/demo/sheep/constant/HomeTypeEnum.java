package com.demo.sheep.constant;

public enum HomeTypeEnum {
	QUOTATION(1,"行情");
	private Integer code;
	private String desc;
	HomeTypeEnum(Integer code, String desc){
		this.code = code;
		this.desc = desc;
	}
	public Integer code(){
		return this.code;
	}
}
