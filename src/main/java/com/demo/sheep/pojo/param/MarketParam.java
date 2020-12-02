package com.demo.sheep.pojo.param;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class MarketParam {
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
    private String fileIds;
}
