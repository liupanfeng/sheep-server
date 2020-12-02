package com.demo.sheep.dao;

import com.demo.sheep.pojo.table.UserCulturalInfo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface UserCulturalInfoMapper {
    @Delete({
        "delete from user_cultural_info",
        "where user_id = #{userId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer userId);

    @Insert({
        "insert into user_cultural_info (user_id, cultural_scale, ",
        "cultural_address, cultural_age, ",
        "current_cultural_quantity, mod_date, ",
        "init_date)",
        "values (#{userId,jdbcType=INTEGER}, #{culturalScale,jdbcType=INTEGER}, ",
        "#{culturalAddress,jdbcType=VARCHAR}, #{culturalAge,jdbcType=INTEGER}, ",
        "#{currentCulturalQuantity,jdbcType=INTEGER}, #{modDate,jdbcType=TIMESTAMP}, ",
        "#{initDate,jdbcType=TIMESTAMP})"
    })
    int insert(UserCulturalInfo record);

    @InsertProvider(type= UserCulturalInfoSqlProvider.class, method="insertSelective")
    int insertSelective(UserCulturalInfo record);

    @Select({
        "select",
        "user_id, cultural_scale, cultural_address, cultural_age, current_cultural_quantity, ",
        "mod_date, init_date",
        "from user_cultural_info",
        "where user_id = #{userId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="cultural_scale", property="culturalScale", jdbcType=JdbcType.INTEGER),
        @Result(column="cultural_address", property="culturalAddress", jdbcType=JdbcType.VARCHAR),
        @Result(column="cultural_age", property="culturalAge", jdbcType=JdbcType.INTEGER),
        @Result(column="current_cultural_quantity", property="currentCulturalQuantity", jdbcType=JdbcType.INTEGER),
        @Result(column="mod_date", property="modDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="init_date", property="initDate", jdbcType=JdbcType.TIMESTAMP)
    })
    UserCulturalInfo selectByPrimaryKey(Integer userId);

    @UpdateProvider(type= UserCulturalInfoSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UserCulturalInfo record);

    @Update({
        "update user_cultural_info",
        "set cultural_scale = #{culturalScale,jdbcType=INTEGER},",
          "cultural_address = #{culturalAddress,jdbcType=VARCHAR},",
          "cultural_age = #{culturalAge,jdbcType=INTEGER},",
          "current_cultural_quantity = #{currentCulturalQuantity,jdbcType=INTEGER},",
          "mod_date = #{modDate,jdbcType=TIMESTAMP},",
          "init_date = #{initDate,jdbcType=TIMESTAMP}",
        "where user_id = #{userId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(UserCulturalInfo record);
}