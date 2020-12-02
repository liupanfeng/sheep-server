package com.demo.sheep.dao;

import com.demo.sheep.pojo.param.PageParam;
import com.demo.sheep.pojo.table.News;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface NewsMapper {
    @Delete({
        "delete from news",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into news (id, title, ",
        "author, publish_date, ",
        "news_source, init_date, ",
        "init_user, mod_date, ",
        "mod_user, state)",
        "values (#{id,jdbcType=INTEGER}, #{title,jdbcType=VARCHAR}, ",
        "#{author,jdbcType=VARCHAR}, #{publishDate,jdbcType=TIMESTAMP}, ",
        "#{newsSource,jdbcType=VARCHAR}, #{initDate,jdbcType=TIMESTAMP}, ",
        "#{initUser,jdbcType=INTEGER}, #{modDate,jdbcType=TIMESTAMP}, ",
        "#{modUser,jdbcType=INTEGER}, #{state,jdbcType=INTEGER})"
    })
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int insert(News record);

    @InsertProvider(type=NewsSqlProvider.class, method="insertSelective")
    int insertSelective(News record);


    @Select({
            "select",
            "id, title, author, publish_date, news_source, init_date, init_user, mod_date, ",
            "mod_user, state",
            "from news",
            "where state = #{state,jdbcType=INTEGER} order by init_date desc limit #{page.startIndex},#{page.pageSize}"
    })
    List<News> queryByPage(@Param("state")Integer state, @Param("page")PageParam page);

    @Select({
        "select",
        "id, title, author, publish_date, news_source, init_date, init_user, mod_date, ",
        "mod_user, state",
        "from news",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="author", property="author", jdbcType=JdbcType.VARCHAR),
        @Result(column="publish_date", property="publishDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="news_source", property="newsSource", jdbcType=JdbcType.VARCHAR),
        @Result(column="init_date", property="initDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="init_user", property="initUser", jdbcType=JdbcType.INTEGER),
        @Result(column="mod_date", property="modDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="mod_user", property="modUser", jdbcType=JdbcType.INTEGER),
        @Result(column="state", property="state", jdbcType=JdbcType.INTEGER)
    })
    News selectByPrimaryKey(Integer id);

    @UpdateProvider(type=NewsSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(News record);

    @Update({
        "update news",
        "set title = #{title,jdbcType=VARCHAR},",
          "author = #{author,jdbcType=VARCHAR},",
          "publish_date = #{publishDate,jdbcType=TIMESTAMP},",
          "news_source = #{newsSource,jdbcType=VARCHAR},",
          "init_date = #{initDate,jdbcType=TIMESTAMP},",
          "init_user = #{initUser,jdbcType=INTEGER},",
          "mod_date = #{modDate,jdbcType=TIMESTAMP},",
          "mod_user = #{modUser,jdbcType=INTEGER},",
          "state = #{state,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(News record);
}