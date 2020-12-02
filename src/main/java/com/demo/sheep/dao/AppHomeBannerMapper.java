package com.demo.sheep.dao;

import com.demo.sheep.pojo.table.AppHomeBanner;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface AppHomeBannerMapper {
    @Delete({
        "delete from app_home_banner",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into app_home_banner (id, news_id, ",
        "title, ",
        "init_date, init_user)",
        "values (#{id,jdbcType=INTEGER}, #{newsId,jdbcType=INTEGER}, ",
        "#{title,jdbcType=VARCHAR}, ",
        "#{initDate,jdbcType=TIMESTAMP}, #{initUser,jdbcType=INTEGER})"
    })
    int insert(AppHomeBanner record);


    @Select({
        "select",
        "id, news_id, title, init_date, init_user",
        "from app_home_banner",
        "where id = #{id,jdbcType=INTEGER}"
    })
    AppHomeBanner selectByPrimaryKey(Integer id);

    @Select({
            "select",
            "id,news_id, title",
            "from app_home_banner",
            "order by init_date desc limit 4"
    })
    List<Map<String,Object>> queryForHome();
}