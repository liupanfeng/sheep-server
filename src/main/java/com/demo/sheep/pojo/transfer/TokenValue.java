package com.demo.sheep.pojo.transfer;

import com.demo.sheep.constant.UserLoginTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class TokenValue implements Serializable {
    private Integer userId;
    private UserLoginTypeEnum loginType;
}
