package com.demo.sheep.dao;

import com.demo.sheep.pojo.table.QuotationDayRecord;
import org.apache.ibatis.jdbc.SQL;

public class QuotationDayRecordSqlProvider {

    public String insertSelective(QuotationDayRecord record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("quotation_day_record");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getQuotationId() != null) {
            sql.VALUES("quotation_id", "#{quotationId,jdbcType=INTEGER}");
        }
        
        if (record.getTotalPrice() != null) {
            sql.VALUES("total_price", "#{totalPrice,jdbcType=DECIMAL}");
        }
        
        if (record.getAveragePrice() != null) {
            sql.VALUES("average_price", "#{averagePrice,jdbcType=DECIMAL}");
        }
        
        if (record.getInitDay() != null) {
            sql.VALUES("init_day", "#{initDay,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(QuotationDayRecord record) {
        SQL sql = new SQL();
        sql.UPDATE("quotation_day_record");
        
        if (record.getQuotationId() != null) {
            sql.SET("quotation_id = #{quotationId,jdbcType=INTEGER}");
        }
        
        if (record.getTotalPrice() != null) {
            sql.SET("total_price = #{totalPrice,jdbcType=DECIMAL}");
        }
        
        if (record.getAveragePrice() != null) {
            sql.SET("average_price = #{averagePrice,jdbcType=DECIMAL}");
        }
        
        if (record.getInitDay() != null) {
            sql.SET("init_day = #{initDay,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}