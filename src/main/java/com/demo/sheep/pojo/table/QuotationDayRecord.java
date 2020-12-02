package com.demo.sheep.pojo.table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QuotationDayRecord {
    private Integer id;

    private Integer quotationId;

    private Double totalPrice;

    private Double averagePrice;

    private String initDay;

}