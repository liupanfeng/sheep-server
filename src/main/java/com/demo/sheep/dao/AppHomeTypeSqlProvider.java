package com.demo.sheep.dao;

import com.demo.sheep.pojo.table.AppHomeType;
import org.apache.ibatis.jdbc.SQL;

public class AppHomeTypeSqlProvider {


    public String insertSelective(AppHomeType record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("app_home_type");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getLevel() != null) {
            sql.VALUES("level", "#{level,jdbcType=TINYINT}");
        }
        
        if (record.getParentId() != null) {
            sql.VALUES("parent_id", "#{parentId,jdbcType=INTEGER}");
        }
        
        if (record.getState() != null) {
            sql.VALUES("state", "#{state,jdbcType=TINYINT}");
        }
        
        if (record.getOrderIndex() != null) {
            sql.VALUES("order_index", "#{orderIndex,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(AppHomeType record) {
        SQL sql = new SQL();
        sql.UPDATE("app_home_type");
        
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getLevel() != null) {
            sql.SET("level = #{level,jdbcType=TINYINT}");
        }
        
        if (record.getParentId() != null) {
            sql.SET("parent_id = #{parentId,jdbcType=INTEGER}");
        }
        
        if (record.getState() != null) {
            sql.SET("state = #{state,jdbcType=TINYINT}");
        }
        
        if (record.getOrderIndex() != null) {
            sql.SET("order_index = #{orderIndex,jdbcType=INTEGER}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}