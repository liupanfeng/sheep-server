package com.demo.sheep.dao;

import com.demo.sheep.pojo.table.MarketRecommend;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface MarketRecommendMapper {
    @Delete({
        "delete from market_recommend",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Delete({
            "delete from market_recommend",
            "where market_id = #{marketId,jdbcType=INTEGER}"
    })
    int deleteByMarketId(Integer marketId);

    @Insert({
        "insert into market_recommend (id, type_id, ",
        "market_id, order_score, ",
        "init_date, init_user)",
        "values (#{id,jdbcType=INTEGER}, #{typeId,jdbcType=INTEGER}, ",
        "#{marketId,jdbcType=INTEGER}, #{orderScore,jdbcType=INTEGER}, ",
        "#{initDate,jdbcType=TIMESTAMP}, #{initUser,jdbcType=INTEGER})"
    })
    int insert(MarketRecommend record);

    @InsertProvider(type=MarketRecommendSqlProvider.class, method="insertSelective")
    int insertSelective(MarketRecommend record);

    @Select({
        "select",
        "id, type_id, market_id, order_score, init_date, init_user",
        "from market_recommend",
        "where id = #{id,jdbcType=INTEGER}"
    })
    MarketRecommend selectByPrimaryKey(Integer id);

    @Select({
            "select",
            "id from market_recommend",
            "where market_id = #{marketId,jdbcType=INTEGER} limit 1"
    })
    Integer selectByMarketId(Integer marketId);

    @UpdateProvider(type=MarketRecommendSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(MarketRecommend record);

    @Update({
        "update market_recommend",
        "set type_id = #{typeId,jdbcType=INTEGER},",
          "market_id = #{marketId,jdbcType=INTEGER},",
          "order_score = #{orderScore,jdbcType=INTEGER},",
          "init_date = #{initDate,jdbcType=TIMESTAMP},",
          "init_user = #{initUser,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(MarketRecommend record);
}