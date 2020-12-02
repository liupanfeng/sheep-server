package com.demo.sheep.pojo.table;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
public class Market {
    private Integer id;

    private Integer typeId;

    private String title;

    private String description;

    private String variety;

    private Double weight;

    private Integer amount;

    private Double price;

    private String phone;

    private String address;

    private Integer longitude;

    private Integer latitude;

    private Integer authState;

    private Integer state;

    private Date initDate;

    private Integer initUser;

    private Date modDate;

    private Integer modUser;

    private Date authDate;

    private Integer authUser;
}