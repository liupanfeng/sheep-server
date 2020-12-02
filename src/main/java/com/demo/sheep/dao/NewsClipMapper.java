package com.demo.sheep.dao;

import com.demo.sheep.pojo.table.NewsClip;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;
@Mapper
public interface NewsClipMapper {
    @Delete({
        "delete from news_clip",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into news_clip (id, news_id, ",
        "clip_type, content, ",
        "clip_order)",
        "values (#{id,jdbcType=INTEGER}, #{newsId,jdbcType=INTEGER}, ",
        "#{clipType,jdbcType=VARCHAR}, #{content,jdbcType=VARCHAR}, ",
        "#{clipOrder,jdbcType=INTEGER})"
    })
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int insert(NewsClip record);

    @InsertProvider(type=NewsClipSqlProvider.class, method="insertSelective")
    int insertSelective(NewsClip record);

    @Select({
        "select",
        "id, news_id, clip_type, content, clip_order",
        "from news_clip",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="news_id", property="newsId", jdbcType=JdbcType.INTEGER),
        @Result(column="clip_type", property="clipType", jdbcType=JdbcType.VARCHAR),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="clip_order", property="clipOrder", jdbcType=JdbcType.INTEGER)
    })
    NewsClip selectByPrimaryKey(Integer id);

    @Select({
            "select",
            "id, news_id, clip_type, content, clip_order",
            "from news_clip",
            "where news_id = #{newsId,jdbcType=INTEGER} order by clip_order"
    })
    List<NewsClip> queryByNewsId(Integer newsId);

    @UpdateProvider(type=NewsClipSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(NewsClip record);

    @Update({
        "update news_clip",
        "set news_id = #{newsId,jdbcType=INTEGER},",
          "clip_type = #{clipType,jdbcType=VARCHAR},",
          "content = #{content,jdbcType=VARCHAR},",
          "clip_order = #{clipOrder,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(NewsClip record);
}