package com.demo.sheep.dao;

import com.demo.sheep.pojo.table.UserCulturalInfo;
import org.apache.ibatis.jdbc.SQL;

public class UserCulturalInfoSqlProvider {

    public String insertSelective(UserCulturalInfo record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("user_cultural_info");
        
        if (record.getUserId() != null) {
            sql.VALUES("user_id", "#{userId,jdbcType=INTEGER}");
        }
        
        if (record.getCulturalScale() != null) {
            sql.VALUES("cultural_scale", "#{culturalScale,jdbcType=INTEGER}");
        }
        
        if (record.getCulturalAddress() != null) {
            sql.VALUES("cultural_address", "#{culturalAddress,jdbcType=VARCHAR}");
        }
        
        if (record.getCulturalAge() != null) {
            sql.VALUES("cultural_age", "#{culturalAge,jdbcType=INTEGER}");
        }
        
        if (record.getCurrentCulturalQuantity() != null) {
            sql.VALUES("current_cultural_quantity", "#{currentCulturalQuantity,jdbcType=INTEGER}");
        }
        
        if (record.getModDate() != null) {
            sql.VALUES("mod_date", "#{modDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getInitDate() != null) {
            sql.VALUES("init_date", "#{initDate,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(UserCulturalInfo record) {
        SQL sql = new SQL();
        sql.UPDATE("user_cultural_info");
        
        if (record.getCulturalScale() != null) {
            sql.SET("cultural_scale = #{culturalScale,jdbcType=INTEGER}");
        }
        
        if (record.getCulturalAddress() != null) {
            sql.SET("cultural_address = #{culturalAddress,jdbcType=VARCHAR}");
        }
        
        if (record.getCulturalAge() != null) {
            sql.SET("cultural_age = #{culturalAge,jdbcType=INTEGER}");
        }
        
        if (record.getCurrentCulturalQuantity() != null) {
            sql.SET("current_cultural_quantity = #{currentCulturalQuantity,jdbcType=INTEGER}");
        }
        
        if (record.getModDate() != null) {
            sql.SET("mod_date = #{modDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getInitDate() != null) {
            sql.SET("init_date = #{initDate,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("user_id = #{userId,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}