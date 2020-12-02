package com.demo.sheep.dao;

import com.demo.sheep.pojo.table.Service;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface ServiceMapper {

    @Insert({
        "insert into service (id, type_id, ",
        "team_name, team_human_scale, ",
        "team_car_scale, team_desc, ",
        "price, phone, car_type, ",
        "service_type, car_volume, ",
        "max_height, state, ",
        "init_date, init_user, ",
        "mod_date, mod_user, ",
        "auth_state, auth_date, ",
        "auth_user)",
        "values (#{id,jdbcType=INTEGER}, #{typeId,jdbcType=INTEGER}, ",
        "#{teamName,jdbcType=VARCHAR}, #{teamHumanScale,jdbcType=INTEGER}, ",
        "#{teamCarScale,jdbcType=INTEGER}, #{teamDesc,jdbcType=VARCHAR}, ",
        "#{price,jdbcType=DECIMAL}, #{phone,jdbcType=CHAR}, #{carType,jdbcType=INTEGER}, ",
        "#{serviceType,jdbcType=VARCHAR}, #{carVolume,jdbcType=VARCHAR}, ",
        "#{maxHeight,jdbcType=DECIMAL}, #{state,jdbcType=INTEGER}, ",
        "#{initDate,jdbcType=TIMESTAMP}, #{initUser,jdbcType=INTEGER}, ",
        "#{modDate,jdbcType=TIMESTAMP}, #{modUser,jdbcType=INTEGER}, ",
        "#{authState,jdbcType=INTEGER}, #{authDate,jdbcType=TIMESTAMP}, ",
        "#{authUser,jdbcType=INTEGER})"
    })
    int insert(Service record);

    @InsertProvider(type=ServiceSqlProvider.class, method="insertSelective")
    int insertSelective(Service record);

    @Select({
        "select",
        "id, type_id, team_name, team_human_scale, team_car_scale, team_desc, price, ",
        "phone, car_type, service_type, car_volume, max_height, state, init_date, init_user, ",
        "mod_date, mod_user, auth_state, auth_date, auth_user",
        "from service",
        "where id = #{id,jdbcType=INTEGER}"
    })
    Service selectByPrimaryKey(Integer id);

    @UpdateProvider(type=ServiceSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Service record);

    @Update({
        "update service",
        "set type_id = #{typeId,jdbcType=INTEGER},",
          "team_name = #{teamName,jdbcType=VARCHAR},",
          "team_human_scale = #{teamHumanScale,jdbcType=INTEGER},",
          "team_car_scale = #{teamCarScale,jdbcType=INTEGER},",
          "team_desc = #{teamDesc,jdbcType=VARCHAR},",
          "price = #{price,jdbcType=DECIMAL},",
          "phone = #{phone,jdbcType=CHAR},",
          "car_type = #{carType,jdbcType=INTEGER},",
          "service_type = #{serviceType,jdbcType=VARCHAR},",
          "car_volume = #{carVolume,jdbcType=VARCHAR},",
          "max_height = #{maxHeight,jdbcType=DECIMAL},",
          "state = #{state,jdbcType=INTEGER},",
          "init_date = #{initDate,jdbcType=TIMESTAMP},",
          "init_user = #{initUser,jdbcType=INTEGER},",
          "mod_date = #{modDate,jdbcType=TIMESTAMP},",
          "mod_user = #{modUser,jdbcType=INTEGER},",
          "auth_state = #{authState,jdbcType=INTEGER},",
          "auth_date = #{authDate,jdbcType=TIMESTAMP},",
          "auth_user = #{authUser,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Service record);
}