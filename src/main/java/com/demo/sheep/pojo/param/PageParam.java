package com.demo.sheep.pojo.param;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageParam {
	private Integer pageSize;
	private Integer pageNum;
	private Integer startIndex;

}
