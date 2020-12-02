package com.demo.sheep.constant;

public enum TimeThresholdTypeEnum {
	SEVEN(7),
	THIRTY(30);
	private int value;
	TimeThresholdTypeEnum(int value){
		this.value = value;
	}

	public int value(){
		return this.value;
	}

	public static TimeThresholdTypeEnum check(String code){
		for (TimeThresholdTypeEnum modeEnum : TimeThresholdTypeEnum.values()) {
			if(modeEnum.name().equalsIgnoreCase(code)){
				return modeEnum;
			}
		}
		return null;
	}
}
