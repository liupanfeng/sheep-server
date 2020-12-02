package com.demo.sheep.dao;

import com.demo.sheep.pojo.param.PageParam;
import com.demo.sheep.pojo.table.Quotation;
import org.apache.ibatis.jdbc.SQL;

public class QuotationSqlProvider {

    public String queryByPage(){
        String sql = "select * from quotation where type = #{type} and state = #{state} " +
                "order by mod_date desc limit #{startIndex},#{pageSize}";
        return sql;
    }

    public String updateByPrimaryKeySelective(Quotation record) {
        SQL sql = new SQL();
        sql.UPDATE("quotation");
        
        if (record.getTypeId() != null) {
            sql.SET("type_id = #{typeId,jdbcType=INTEGER}");
        }
        
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getPlace() != null) {
            sql.SET("place = #{place,jdbcType=VARCHAR}");
        }
        
        if (record.getSpecification() != null) {
            sql.SET("specification = #{specification,jdbcType=INTEGER}");
        }
        
        if (record.getInitDate() != null) {
            sql.SET("init_date = #{initDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getInitUser() != null) {
            sql.SET("init_user = #{initUser,jdbcType=INTEGER}");
        }
        
        if (record.getModDate() != null) {
            sql.SET("mod_date = #{modDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModUser() != null) {
            sql.SET("mod_user = #{modUser,jdbcType=INTEGER}");
        }
        
        if (record.getState() != null) {
            sql.SET("state = #{state,jdbcType=TINYINT}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}