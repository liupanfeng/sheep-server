package com.demo.sheep.pojo.table;

import java.util.Date;

public class MarketRecommend {
    private Integer id;

    private Integer typeId;

    private Integer marketId;

    private Integer orderScore;

    private Date initDate;

    private Integer initUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getMarketId() {
        return marketId;
    }

    public void setMarketId(Integer marketId) {
        this.marketId = marketId;
    }

    public Integer getOrderScore() {
        return orderScore;
    }

    public void setOrderScore(Integer orderScore) {
        this.orderScore = orderScore;
    }

    public Date getInitDate() {
        return initDate;
    }

    public void setInitDate(Date initDate) {
        this.initDate = initDate;
    }

    public Integer getInitUser() {
        return initUser;
    }

    public void setInitUser(Integer initUser) {
        this.initUser = initUser;
    }
}