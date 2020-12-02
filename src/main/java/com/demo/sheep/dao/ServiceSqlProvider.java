package com.demo.sheep.dao;

import com.demo.sheep.pojo.table.Service;
import org.apache.ibatis.jdbc.SQL;

public class ServiceSqlProvider {

    public String insertSelective(Service record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("service");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getTypeId() != null) {
            sql.VALUES("type_id", "#{typeId,jdbcType=INTEGER}");
        }
        
        if (record.getTeamName() != null) {
            sql.VALUES("team_name", "#{teamName,jdbcType=VARCHAR}");
        }
        
        if (record.getTeamHumanScale() != null) {
            sql.VALUES("team_human_scale", "#{teamHumanScale,jdbcType=INTEGER}");
        }
        
        if (record.getTeamCarScale() != null) {
            sql.VALUES("team_car_scale", "#{teamCarScale,jdbcType=INTEGER}");
        }
        
        if (record.getTeamDesc() != null) {
            sql.VALUES("team_desc", "#{teamDesc,jdbcType=VARCHAR}");
        }
        
        if (record.getPrice() != null) {
            sql.VALUES("price", "#{price,jdbcType=DECIMAL}");
        }
        
        if (record.getPhone() != null) {
            sql.VALUES("phone", "#{phone,jdbcType=CHAR}");
        }
        
        if (record.getCarType() != null) {
            sql.VALUES("car_type", "#{carType,jdbcType=INTEGER}");
        }
        
        if (record.getServiceType() != null) {
            sql.VALUES("service_type", "#{serviceType,jdbcType=VARCHAR}");
        }
        
        if (record.getCarVolume() != null) {
            sql.VALUES("car_volume", "#{carVolume,jdbcType=VARCHAR}");
        }
        
        if (record.getMaxHeight() != null) {
            sql.VALUES("max_height", "#{maxHeight,jdbcType=DECIMAL}");
        }
        
        if (record.getState() != null) {
            sql.VALUES("state", "#{state,jdbcType=INTEGER}");
        }
        
        if (record.getInitDate() != null) {
            sql.VALUES("init_date", "#{initDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getInitUser() != null) {
            sql.VALUES("init_user", "#{initUser,jdbcType=INTEGER}");
        }
        
        if (record.getModDate() != null) {
            sql.VALUES("mod_date", "#{modDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModUser() != null) {
            sql.VALUES("mod_user", "#{modUser,jdbcType=INTEGER}");
        }
        
        if (record.getAuthState() != null) {
            sql.VALUES("auth_state", "#{authState,jdbcType=INTEGER}");
        }
        
        if (record.getAuthDate() != null) {
            sql.VALUES("auth_date", "#{authDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getAuthUser() != null) {
            sql.VALUES("auth_user", "#{authUser,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Service record) {
        SQL sql = new SQL();
        sql.UPDATE("service");
        
        if (record.getTypeId() != null) {
            sql.SET("type_id = #{typeId,jdbcType=INTEGER}");
        }
        
        if (record.getTeamName() != null) {
            sql.SET("team_name = #{teamName,jdbcType=VARCHAR}");
        }
        
        if (record.getTeamHumanScale() != null) {
            sql.SET("team_human_scale = #{teamHumanScale,jdbcType=INTEGER}");
        }
        
        if (record.getTeamCarScale() != null) {
            sql.SET("team_car_scale = #{teamCarScale,jdbcType=INTEGER}");
        }
        
        if (record.getTeamDesc() != null) {
            sql.SET("team_desc = #{teamDesc,jdbcType=VARCHAR}");
        }
        
        if (record.getPrice() != null) {
            sql.SET("price = #{price,jdbcType=DECIMAL}");
        }
        
        if (record.getPhone() != null) {
            sql.SET("phone = #{phone,jdbcType=CHAR}");
        }
        
        if (record.getCarType() != null) {
            sql.SET("car_type = #{carType,jdbcType=INTEGER}");
        }
        
        if (record.getServiceType() != null) {
            sql.SET("service_type = #{serviceType,jdbcType=VARCHAR}");
        }
        
        if (record.getCarVolume() != null) {
            sql.SET("car_volume = #{carVolume,jdbcType=VARCHAR}");
        }
        
        if (record.getMaxHeight() != null) {
            sql.SET("max_height = #{maxHeight,jdbcType=DECIMAL}");
        }
        
        if (record.getState() != null) {
            sql.SET("state = #{state,jdbcType=INTEGER}");
        }
        
        if (record.getInitDate() != null) {
            sql.SET("init_date = #{initDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getInitUser() != null) {
            sql.SET("init_user = #{initUser,jdbcType=INTEGER}");
        }
        
        if (record.getModDate() != null) {
            sql.SET("mod_date = #{modDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModUser() != null) {
            sql.SET("mod_user = #{modUser,jdbcType=INTEGER}");
        }
        
        if (record.getAuthState() != null) {
            sql.SET("auth_state = #{authState,jdbcType=INTEGER}");
        }
        
        if (record.getAuthDate() != null) {
            sql.SET("auth_date = #{authDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getAuthUser() != null) {
            sql.SET("auth_user = #{authUser,jdbcType=INTEGER}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}