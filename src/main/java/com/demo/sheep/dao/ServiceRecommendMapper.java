package com.demo.sheep.dao;

import com.demo.sheep.pojo.table.ServiceRecommend;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface ServiceRecommendMapper {
    @Delete({
        "delete from service_recommend",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into service_recommend (id, type_id, ",
        "service_id, order_score, ",
        "init_date, init_user)",
        "values (#{id,jdbcType=INTEGER}, #{typeId,jdbcType=INTEGER}, ",
        "#{serviceId,jdbcType=INTEGER}, #{orderScore,jdbcType=INTEGER}, ",
        "#{initDate,jdbcType=TIMESTAMP}, #{initUser,jdbcType=INTEGER})"
    })
    int insert(ServiceRecommend record);

    @InsertProvider(type=ServiceRecommendSqlProvider.class, method="insertSelective")
    int insertSelective(ServiceRecommend record);

    @Select({
        "select",
        "id, type_id, service_id, order_score, init_date, init_user",
        "from service_recommend",
        "where id = #{id,jdbcType=INTEGER}"
    })
    ServiceRecommend selectByPrimaryKey(Integer id);

    @UpdateProvider(type=ServiceRecommendSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ServiceRecommend record);

    @Update({
        "update service_recommend",
        "set type_id = #{typeId,jdbcType=INTEGER},",
          "service_id = #{serviceId,jdbcType=INTEGER},",
          "order_score = #{orderScore,jdbcType=INTEGER},",
          "init_date = #{initDate,jdbcType=TIMESTAMP},",
          "init_user = #{initUser,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ServiceRecommend record);
}