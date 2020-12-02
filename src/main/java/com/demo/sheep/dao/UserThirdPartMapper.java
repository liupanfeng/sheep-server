package com.demo.sheep.dao;

import com.demo.sheep.pojo.table.UserThirdPart;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface UserThirdPartMapper {
    @Delete({
        "delete from user_third_part",
        "where user_id = #{userId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer userId);

    @Insert({
        "insert into user_third_part (user_id, third_part_id, ",
        "third_part_user_id, init_date, ",
        "mod_date)",
        "values (#{userId,jdbcType=INTEGER}, #{thirdPartId,jdbcType=VARCHAR}, ",
        "#{thirdPartUserId,jdbcType=VARCHAR}, #{initDate,jdbcType=TIMESTAMP}, ",
        "#{modDate,jdbcType=TIMESTAMP})"
    })
    int insert(UserThirdPart record);

    @InsertProvider(type=UserThirdPartSqlProvider.class, method="insertSelective")
    int insertSelective(UserThirdPart record);

    @Select({
        "select",
        "user_id, third_part_id, third_part_user_id, init_date, mod_date",
        "from user_third_part",
        "where user_id = #{userId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="third_part_id", property="thirdPartId", jdbcType=JdbcType.VARCHAR),
        @Result(column="third_part_user_id", property="thirdPartUserId", jdbcType=JdbcType.VARCHAR),
        @Result(column="init_date", property="initDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="mod_date", property="modDate", jdbcType=JdbcType.TIMESTAMP)
    })
    UserThirdPart selectByPrimaryKey(Integer userId);

    @UpdateProvider(type=UserThirdPartSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UserThirdPart record);

    @Update({
        "update user_third_part",
        "set third_part_id = #{thirdPartId,jdbcType=VARCHAR},",
          "third_part_user_id = #{thirdPartUserId,jdbcType=VARCHAR},",
          "init_date = #{initDate,jdbcType=TIMESTAMP},",
          "mod_date = #{modDate,jdbcType=TIMESTAMP}",
        "where user_id = #{userId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(UserThirdPart record);
}