package com.demo.sheep.pojo.table;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class UserThirdPart {
    private Integer userId;

    private String thirdPartId;

    private String thirdPartUserId;

    private Date initDate;

    private Date modDate;

}