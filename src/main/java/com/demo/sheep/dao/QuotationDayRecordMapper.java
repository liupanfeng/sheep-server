package com.demo.sheep.dao;

import com.demo.sheep.pojo.table.QuotationDayRecord;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface QuotationDayRecordMapper {
    @Delete({
        "delete from quotation_day_record",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into quotation_day_record (id, quotation_id, ",
        "total_price, average_price, ",
        "init_day)",
        "values (#{id,jdbcType=INTEGER}, #{quotationId,jdbcType=INTEGER}, ",
        "#{totalPrice,jdbcType=DECIMAL}, #{averagePrice,jdbcType=DECIMAL}, ",
        "#{initDay,jdbcType=VARCHAR})"
    })
    int insert(QuotationDayRecord record);

    @InsertProvider(type=QuotationDayRecordSqlProvider.class, method="insertSelective")
    int insertSelective(QuotationDayRecord record);

    @Select({
        "select",
        "id, quotation_id, total_price, average_price, init_day",
        "from quotation_day_record",
        "where id = #{id,jdbcType=INTEGER}"
    })
    QuotationDayRecord selectByPrimaryKey(Integer id);

    @UpdateProvider(type=QuotationDayRecordSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(QuotationDayRecord record);

    @Update({
        "update quotation_day_record",
        "set quotation_id = #{quotationId,jdbcType=INTEGER},",
          "total_price = #{totalPrice,jdbcType=DECIMAL},",
          "average_price = #{averagePrice,jdbcType=DECIMAL},",
          "init_day = #{initDay,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(QuotationDayRecord record);

    @Select("SELECT average_price,init_day from quotation_day_record " +
            "where quotation_id = #{quotationId} order by init_day desc limit #{pageSize} ")
    List<HashMap<String,Number>> historyPrice(Integer quotationId,Integer pageSize);

    @Update({
            "update quotation_day_record",
            "set total_price = #{totalPrice,jdbcType=DECIMAL},",
            " average_price = #{averagePrice,jdbcType=DECIMAL}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updatePriceByPrimaryKey(QuotationDayRecord record);

    @Select({
            "select",
            "id",
            "from quotation_day_record",
            "where quotation_id = #{quotationId,jdbcType=INTEGER}",
            "and init_day = #{initDay}"
    })
    QuotationDayRecord selectByQuotationIdAndInitDay(@Param("quotationId") Integer quotationId, @Param("initDay") String initDay);

    @Select({
            "select",
            "sum(average_price) total,count(id) num",
            "from quotation_day_record",
            "where quotation_id = #{quotationId,jdbcType=INTEGER}",
            "and init_day like #{initDay}"
    })
    HashMap<String,Object> selectByQuotationIdAndInitDayByLike(@Param("quotationId") Integer quotationId, @Param("initDay") String initDay);

}