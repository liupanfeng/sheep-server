package com.demo.sheep.dao;

import com.demo.sheep.pojo.table.UserThirdPart;
import org.apache.ibatis.jdbc.SQL;

public class UserThirdPartSqlProvider {

    public String insertSelective(UserThirdPart record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("user_third_part");
        
        if (record.getUserId() != null) {
            sql.VALUES("user_id", "#{userId,jdbcType=INTEGER}");
        }
        
        if (record.getThirdPartId() != null) {
            sql.VALUES("third_part_id", "#{thirdPartId,jdbcType=VARCHAR}");
        }
        
        if (record.getThirdPartUserId() != null) {
            sql.VALUES("third_part_user_id", "#{thirdPartUserId,jdbcType=VARCHAR}");
        }
        
        if (record.getInitDate() != null) {
            sql.VALUES("init_date", "#{initDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModDate() != null) {
            sql.VALUES("mod_date", "#{modDate,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(UserThirdPart record) {
        SQL sql = new SQL();
        sql.UPDATE("user_third_part");
        
        if (record.getThirdPartId() != null) {
            sql.SET("third_part_id = #{thirdPartId,jdbcType=VARCHAR}");
        }
        
        if (record.getThirdPartUserId() != null) {
            sql.SET("third_part_user_id = #{thirdPartUserId,jdbcType=VARCHAR}");
        }
        
        if (record.getInitDate() != null) {
            sql.SET("init_date = #{initDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModDate() != null) {
            sql.SET("mod_date = #{modDate,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("user_id = #{userId,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}