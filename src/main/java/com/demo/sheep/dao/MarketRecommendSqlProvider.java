package com.demo.sheep.dao;

import com.demo.sheep.pojo.table.MarketRecommend;
import org.apache.ibatis.jdbc.SQL;

public class MarketRecommendSqlProvider {

    public String insertSelective(MarketRecommend record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("market_recommend");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getTypeId() != null) {
            sql.VALUES("type_id", "#{typeId,jdbcType=INTEGER}");
        }
        
        if (record.getMarketId() != null) {
            sql.VALUES("market_id", "#{marketId,jdbcType=INTEGER}");
        }
        
        if (record.getOrderScore() != null) {
            sql.VALUES("order_score", "#{orderScore,jdbcType=INTEGER}");
        }
        
        if (record.getInitDate() != null) {
            sql.VALUES("init_date", "#{initDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getInitUser() != null) {
            sql.VALUES("init_user", "#{initUser,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(MarketRecommend record) {
        SQL sql = new SQL();
        sql.UPDATE("market_recommend");
        
        if (record.getTypeId() != null) {
            sql.SET("type_id = #{typeId,jdbcType=INTEGER}");
        }
        
        if (record.getMarketId() != null) {
            sql.SET("market_id = #{marketId,jdbcType=INTEGER}");
        }
        
        if (record.getOrderScore() != null) {
            sql.SET("order_score = #{orderScore,jdbcType=INTEGER}");
        }
        
        if (record.getInitDate() != null) {
            sql.SET("init_date = #{initDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getInitUser() != null) {
            sql.SET("init_user = #{initUser,jdbcType=INTEGER}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}