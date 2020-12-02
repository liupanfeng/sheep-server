package com.demo.sheep.pojo.table;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ServiceRecommend {
    private Integer id;

    private Integer typeId;

    private Integer serviceId;

    private Integer orderScore;

    private Date initDate;

    private Integer initUser;

}