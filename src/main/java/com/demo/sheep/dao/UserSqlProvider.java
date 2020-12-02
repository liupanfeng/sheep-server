package com.demo.sheep.dao;

import com.demo.sheep.pojo.table.User;
import org.apache.ibatis.jdbc.SQL;

public class UserSqlProvider {

    public String updateByPrimaryKeySelective(User record) {
        SQL sql = new SQL();
        sql.UPDATE("user");
        
        if (record.getUsername() != null) {
            sql.SET("username = #{username,jdbcType=VARCHAR}");
        }
        
        if (record.getPhone() != null) {
            sql.SET("phone = #{phone,jdbcType=CHAR}");
        }
        
        if (record.getNickname() != null) {
            sql.SET("nickname = #{nickname,jdbcType=VARCHAR}");
        }
        
        if (record.getPassword() != null) {
            sql.SET("password = #{password,jdbcType=VARCHAR}");
        }
        
        if (record.getGender() != null) {
            sql.SET("gender = #{gender,jdbcType=TINYINT}");
        }
        
        if (record.getIconPath() != null) {
            sql.SET("icon_path = #{iconPath,jdbcType=VARCHAR}");
        }
        
        if (record.getIdCardFrontPath() != null) {
            sql.SET("id_card_front_path = #{idCardFrontPath,jdbcType=VARCHAR}");
        }
        
        if (record.getIdCardReversePath() != null) {
            sql.SET("id_card_reverse_path = #{idCardReversePath,jdbcType=VARCHAR}");
        }
        
        if (record.getPaymentCode() != null) {
            sql.SET("payment_code = #{paymentCode,jdbcType=CHAR}");
        }
        
        if (record.getLastDate() != null) {
            sql.SET("last_date = #{lastDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getInitDate() != null) {
            sql.SET("init_date = #{initDate,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("user_id = #{userId,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}