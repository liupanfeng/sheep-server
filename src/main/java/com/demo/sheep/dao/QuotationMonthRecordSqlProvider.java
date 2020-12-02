package com.demo.sheep.dao;

import com.demo.sheep.pojo.table.QuotationMonthRecord;
import org.apache.ibatis.jdbc.SQL;

public class QuotationMonthRecordSqlProvider {

    public String insertSelective(QuotationMonthRecord record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("quotation_month_record");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getQuotationId() != null) {
            sql.VALUES("quotation_id", "#{quotationId,jdbcType=INTEGER}");
        }
        
        if (record.getAveragePrice() != null) {
            sql.VALUES("average_price", "#{averagePrice,jdbcType=DECIMAL}");
        }
        
        if (record.getInitMonth() != null) {
            sql.VALUES("init_month", "#{initMonth,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(QuotationMonthRecord record) {
        SQL sql = new SQL();
        sql.UPDATE("quotation_month_record");
        
        if (record.getQuotationId() != null) {
            sql.SET("quotation_id = #{quotationId,jdbcType=INTEGER}");
        }
        
        if (record.getAveragePrice() != null) {
            sql.SET("average_price = #{averagePrice,jdbcType=DECIMAL}");
        }
        
        if (record.getInitMonth() != null) {
            sql.SET("init_month = #{initMonth,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}