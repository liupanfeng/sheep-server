package com.demo.sheep.pojo.table;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class Service {
    private Integer id;

    private Integer typeId;

    private String teamName;

    private Integer teamHumanScale;

    private Integer teamCarScale;

    private String teamDesc;

    private BigDecimal price;

    private String phone;

    private Integer carType;

    private String serviceType;

    private String carVolume;

    private BigDecimal maxHeight;

    private Integer state;

    private Date initDate;

    private Integer initUser;

    private Date modDate;

    private Integer modUser;

    private Integer authState;

    private Date authDate;

    private Integer authUser;

}