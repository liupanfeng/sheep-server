package com.demo.sheep.dao;

import com.demo.sheep.pojo.table.QuotationDayRecord;
import com.demo.sheep.pojo.table.QuotationMonthRecord;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
@Mapper
public interface QuotationMonthRecordMapper {
    @Delete({
        "delete from quotation_month_record",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into quotation_month_record (id, quotation_id, ",
        "average_price, init_month)",
        "values (#{id,jdbcType=INTEGER}, #{quotationId,jdbcType=INTEGER}, ",
        "#{averagePrice,jdbcType=DECIMAL}, #{initMonth,jdbcType=VARCHAR})"
    })
    int insert(QuotationMonthRecord record);

    @InsertProvider(type=QuotationMonthRecordSqlProvider.class, method="insertSelective")
    int insertSelective(QuotationMonthRecord record);

    @Select({
        "select",
        "id, quotation_id, average_price, init_month",
        "from quotation_month_record",
        "where id = #{id,jdbcType=INTEGER}"
    })
    QuotationMonthRecord selectByPrimaryKey(Integer id);

    @UpdateProvider(type=QuotationMonthRecordSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(QuotationMonthRecord record);

    @Update({
        "update quotation_month_record",
        "set quotation_id = #{quotationId,jdbcType=INTEGER},",
          "average_price = #{averagePrice,jdbcType=DECIMAL},",
          "init_month = #{initMonth,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(QuotationMonthRecord record);

    @Select({
            "select",
            "id",
            "from quotation_month_record",
            "where quotation_id = #{quotationId,jdbcType=INTEGER}",
            "and init_month = #{initMonth}"
    })
    QuotationMonthRecord selectByQuotationIdAndMonth(Integer quotationId,String initMonth);
    @Update({
            "update quotation_month_record",
            "set average_price = #{averagePrice,jdbcType=DECIMAL}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updatePriceByPrimaryKey(QuotationMonthRecord record);
}