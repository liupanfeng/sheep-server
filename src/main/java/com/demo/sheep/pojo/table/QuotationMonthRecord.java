package com.demo.sheep.pojo.table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QuotationMonthRecord {
    private Integer id;

    private Integer quotationId;

    private Double averagePrice;

    private String initMonth;

}