package com.demo.sheep.dao;

import com.demo.sheep.pojo.table.FileRelate;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface FileRelateMapper {
    @Delete({
        "delete from file_relate",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into file_relate (id, mode_id, ",
        "relate_id, relative_path, ",
        "state, order_score, ",
        "init_date, init_user)",
        "values (#{id,jdbcType=INTEGER}, #{modeId,jdbcType=INTEGER}, ",
        "#{relateId,jdbcType=INTEGER}, #{relativePath,jdbcType=VARCHAR}, ",
        "#{state,jdbcType=INTEGER}, #{orderScore,jdbcType=INTEGER}, ",
        "#{initDate,jdbcType=TIMESTAMP}, #{initUser,jdbcType=INTEGER})"
    })
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int insert(FileRelate record);


    @Select({
        "select",
        "id, mode_id, relate_id, relative_path, state, order_score, init_date, init_user",
        "from file_relate",
        "where id = #{id,jdbcType=INTEGER}"
    })
    FileRelate selectByPrimaryKey(Integer id);

    @Select({
            "select",
            "relative_path ",
            "from file_relate",
            "where mode_id = #{modeId} and relate_id = #{relateId} and state = #{state}",
            "order by order_score "
    })
    List<String> selectByModeAndRelateId(FileRelate record);

    @UpdateProvider(type=FileRelateSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(FileRelate record);

    @Update({
        "update file_relate",
        "set relate_id = #{relateId,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateRelateId(FileRelate record);
}