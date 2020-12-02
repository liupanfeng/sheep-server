package com.demo.sheep.dao;

import com.demo.sheep.pojo.table.AppHomeType;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;
import java.util.Map;

@Mapper
public interface AppHomeTypeMapper {
    @Delete({
        "delete from app_home_type",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into app_home_type (id, name, ",
        "level, parent_id, ",
        "state, order_index)",
        "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{level,jdbcType=TINYINT}, #{parentId,jdbcType=INTEGER}, ",
        "#{state,jdbcType=TINYINT}, #{orderIndex,jdbcType=INTEGER})"
    })
    int insert(AppHomeType record);

    @InsertProvider(type=AppHomeTypeSqlProvider.class, method="insertSelective")
    int insertSelective(AppHomeType record);

    @Select({
        "select",
        "id, name, level, parent_id, state, order_index",
        "from app_home_type",
        "where id = #{id,jdbcType=INTEGER}"
    })
    AppHomeType selectByPrimaryKey(Integer id);

    @Select({
            "select",
            "id, name",
            "from app_home_type",
            "where parent_id = #{parentId,jdbcType=INTEGER}",
            "and state = #{state} order by order_index"
    })
    List<Map<String,Object>> selectByParentId(Integer parentId, Integer state);

    @UpdateProvider(type=AppHomeTypeSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(AppHomeType record);

    @Update({
        "update app_home_type",
        "set name = #{name,jdbcType=VARCHAR},",
          "level = #{level,jdbcType=TINYINT},",
          "parent_id = #{parentId,jdbcType=INTEGER},",
          "state = #{state,jdbcType=TINYINT},",
          "order_index = #{orderIndex,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(AppHomeType record);
}