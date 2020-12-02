package com.demo.sheep.pojo.table;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class UserCulturalInfo {
    private Integer userId;

    private Integer culturalScale;

    private String culturalAddress;

    private Integer culturalAge;

    private Integer currentCulturalQuantity;

    private Date modDate;

    private Date initDate;
}