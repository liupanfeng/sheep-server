package com.demo.sheep.pojo.table;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class User {
    private Integer userId;

    private String username;

    private String phone;

    private String nickname;

    private String password;

    private Integer gender;

    private String iconPath;

    private String idCardFrontPath;

    private String idCardReversePath;

    private String paymentCode;

    private String uuid;

    private Integer state;

    private Date lastDate;

    private Date initDate;


}