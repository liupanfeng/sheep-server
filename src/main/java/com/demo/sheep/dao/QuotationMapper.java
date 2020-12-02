package com.demo.sheep.dao;

import com.demo.sheep.pojo.param.PageParam;
import com.demo.sheep.pojo.table.Quotation;
import org.apache.ibatis.annotations.*;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface QuotationMapper {

    @Insert({
        "insert into quotation (id, type_id, ",
        "name, place, specification, ",
        "init_date, init_user, ",
        "mod_date, mod_user, ",
        "state)",
        "values (#{id,jdbcType=INTEGER}, #{typeId,jdbcType=INTEGER}, ",
        "#{name,jdbcType=VARCHAR}, #{place,jdbcType=VARCHAR}, #{specification,jdbcType=INTEGER}, ",
        "#{initDate,jdbcType=TIMESTAMP}, #{initUser,jdbcType=INTEGER}, ",
        "#{modDate,jdbcType=TIMESTAMP}, #{modUser,jdbcType=INTEGER}, ",
        "#{state,jdbcType=TINYINT})"
    })
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int insert(Quotation record);

    @Select({
        "select",
        "id, type_id, name, place, specification, init_date, init_user, mod_date, mod_user, ",
        "state",
        "from quotation",
        "where id = #{id,jdbcType=INTEGER}"
    })
    Quotation selectByPrimaryKey(Integer id);

    @UpdateProvider(type=QuotationSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Quotation record);

    @Select("select count(*) from quotation where type_id = #{type} and state = #{state} ")
    Integer queryCount(Integer type, Integer state);

    @Select({"SELECT t1.id,t1.name,t1.place,t1.specification,t1.mod_date, t2.total_price",
            "FROM",
            "( SELECT * FROM quotation WHERE type_id = #{type} AND state = #{state} ) t1",
            "LEFT JOIN ",
            "( SELECT * FROM quotation_day_record WHERE init_day = #{today} ) t2 ",
            "ON t1.id = t2.quotation_id ",
            "ORDER BY t1.mod_date DESC limit #{page.startIndex},#{page.pageSize}"})
    List<HashMap<String,Object>> queryByPage(PageParam page, Integer type, Integer state, String today);


    @Update("update quotation set state = #{state},mod_date = #{modDate},mod_user = #{modUser} where id = #{id}")
    void updateStateById(Quotation record);

    @Select({
            "select id from quotation ",
            "where state = #{state,jdbcType=INTEGER} limit #{startIndex},#{pageSize}"
    })
    List<Quotation> selectByPage(Integer state,Integer startIndex,Integer pageSize);



}