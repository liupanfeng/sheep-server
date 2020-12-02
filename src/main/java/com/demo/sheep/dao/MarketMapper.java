package com.demo.sheep.dao;

import com.demo.sheep.pojo.param.PageParam;
import com.demo.sheep.pojo.table.Market;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;
import java.util.Map;

@Mapper
public interface MarketMapper {

    @Insert({
        "insert into market (id, type_id, ",
        "title, description, ",
        "variety, weight, ",
        "amount, price, phone, ",
        "address, longitude, ",
        "latitude, auth_state, ",
        "state, init_date, ",
        "init_user, mod_date, ",
        "mod_user, auth_date, ",
        "auth_user)",
        "values (#{id,jdbcType=INTEGER}, #{typeId,jdbcType=INTEGER}, ",
        "#{title,jdbcType=VARCHAR}, #{description,jdbcType=VARCHAR}, ",
        "#{variety,jdbcType=VARCHAR}, #{weight,jdbcType=DECIMAL}, ",
        "#{amount,jdbcType=INTEGER}, #{price,jdbcType=DECIMAL}, #{phone,jdbcType=CHAR}, ",
        "#{address,jdbcType=VARCHAR}, #{longitude,jdbcType=INTEGER}, ",
        "#{latitude,jdbcType=INTEGER}, #{authState,jdbcType=INTEGER}, ",
        "#{state,jdbcType=INTEGER}, #{initDate,jdbcType=TIMESTAMP}, ",
        "#{initUser,jdbcType=INTEGER}, #{modDate,jdbcType=TIMESTAMP}, ",
        "#{modUser,jdbcType=INTEGER}, #{authDate,jdbcType=TIMESTAMP}, ",
        "#{authUser,jdbcType=INTEGER})"
    })
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int insert(Market record);

    @InsertProvider(type=MarketSqlProvider.class, method="insertSelective")
    int insertSelective(Market record);

    @Select({
        "select",
        "id, type_id, title, description, variety, weight, amount, price, phone, address, ",
        "longitude, latitude, auth_state, state, init_date, init_user, mod_date, mod_user, ",
        "auth_date, auth_user",
        "from market",
        "where id = #{id,jdbcType=INTEGER}"
    })
    Market selectByPrimaryKey(Integer id);


    @SelectProvider(type=MarketSqlProvider.class, method="selectByTypeIdAndAuthState")
    List<Map<String,Object>> selectByTypeIdAndAuthState(Market record, PageParam page, boolean isRecommend);


    @UpdateProvider(type=MarketSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Market record);

    @Update({
        "update market",
        "set type_id = #{typeId,jdbcType=INTEGER},",
          "title = #{title,jdbcType=VARCHAR},",
          "description = #{description,jdbcType=VARCHAR},",
          "variety = #{variety,jdbcType=VARCHAR},",
          "weight = #{weight,jdbcType=DECIMAL},",
          "amount = #{amount,jdbcType=INTEGER},",
          "price = #{price,jdbcType=DECIMAL},",
          "phone = #{phone,jdbcType=CHAR},",
          "address = #{address,jdbcType=VARCHAR},",
          "longitude = #{longitude,jdbcType=INTEGER},",
          "latitude = #{latitude,jdbcType=INTEGER},",
          "auth_state = #{authState,jdbcType=INTEGER},",
          "state = #{state,jdbcType=INTEGER},",
          "init_date = #{initDate,jdbcType=TIMESTAMP},",
          "init_user = #{initUser,jdbcType=INTEGER},",
          "mod_date = #{modDate,jdbcType=TIMESTAMP},",
          "mod_user = #{modUser,jdbcType=INTEGER},",
          "auth_date = #{authDate,jdbcType=TIMESTAMP},",
          "auth_user = #{authUser,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Market record);

    @Update({
            "update market",
            "set auth_state = #{authState,jdbcType=INTEGER},",
            "auth_date = #{authDate,jdbcType=TIMESTAMP},",
            "auth_user = #{authUser,jdbcType=INTEGER}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    void updateAuthInfo (Market record);

    @Update({
            "update market",
            "set state = #{state,jdbcType=INTEGER},",
            "mod_date = #{modDate,jdbcType=TIMESTAMP},",
            "mod_user = #{modUser,jdbcType=INTEGER}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    void updateState(Market record);
}