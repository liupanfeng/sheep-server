package com.demo.sheep.pojo.table;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class Quotation {
    private Integer id;

    private Integer typeId;

    private String name;

    private String place;

    private Integer specification;

    private Date initDate;

    private Integer initUser;

    private Date modDate;

    private Integer modUser;

    private Integer state;


}