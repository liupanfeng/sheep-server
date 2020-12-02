package com.demo.sheep.pojo.table;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class AppHomeBanner {
    private Integer id;

    private Integer newsId;

    private Date initDate;

    private Integer initUser;

}