package com.demo.sheep.pojo.view;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
public class UserDTO implements Serializable {
	private Integer userId;
	private String nickname;
	private Date initDate;
	private Date lastDate;
}
