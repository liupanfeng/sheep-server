package com.demo.sheep.dao;

import com.demo.sheep.pojo.table.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.Date;
import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select user_id,username,password,nickname from user where username = #{username}")
    User selectByUsername(String username);

    @Insert({
        "insert into user (user_id, username, ",
        "phone, nickname, password, ",
        "gender, icon_path, ",
        "id_card_front_path, id_card_reverse_path, ",
        "payment_code, uuid,state, last_date, ",
        "init_date)",
        "values (#{userId,jdbcType=INTEGER}, #{username,jdbcType=VARCHAR}, ",
        "#{phone,jdbcType=CHAR}, #{nickname,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR}, ",
        "#{gender,jdbcType=TINYINT}, #{iconPath,jdbcType=VARCHAR}, ",
        "#{idCardFrontPath,jdbcType=VARCHAR}, #{idCardReversePath,jdbcType=VARCHAR}, ",
        "#{paymentCode,jdbcType=CHAR}, #{uuid,jdbcType=CHAR},#{state,jdbcType=TINYINT}, #{lastDate,jdbcType=TIMESTAMP}, ",
        "#{initDate,jdbcType=TIMESTAMP})"
    })
    @Options(useGeneratedKeys=true, keyProperty="userId", keyColumn="user_id")
    int insert(User record);

    @Select({
        "select",
        "user_id, username, phone, nickname, password, gender, icon_path, id_card_front_path, ",
        "id_card_reverse_path, payment_code,uuid, state, last_date, init_date",
        "from user",
        "where user_id = #{userId,jdbcType=INTEGER}"
    })
    User selectByPrimaryKey(Integer userId);


    @Select("select * from user where phone = #{phone}")
    User selectUserByPhone(String phone);


    @Update("update user set last_date = #{lastDate} where user_id = #{userId}")
    void updateUserLastDate(@Param("userId") Integer userId, @Param("lastDate") Date lastDate);


    @UpdateProvider(type=UserSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(User record);

}