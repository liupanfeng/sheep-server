package com.demo.sheep.dao;

import com.demo.sheep.pojo.table.Market;
import org.apache.ibatis.jdbc.SQL;

public class MarketSqlProvider {

    public String selectByTypeIdAndAuthState(Market record, boolean isRecommend){
        StringBuilder sql = new StringBuilder();
        sql.append("select m.id, m.title, m.description, m.variety, m.weight, m.amount, m.price, m.phone, m.address,")
                .append("m.auth_state, m.init_date,u.nickname ");
        if(isRecommend){
            sql.append("from market_recommend r left join market m on r.market_id = m.id ");
        }else{
            sql.append("from market m ");
        }
        sql.append("left join user u on m.init_user = u.user_id ")
                .append("where m.type_id = #{record.typeId} ")
                .append("and m.state = #{record.state} ");
        if(record.getAuthState() != null){
            sql.append("and m.auth_state = #{record.authState} ");
        }
        if(isRecommend){
            sql.append("order by r.order_score ");
        }else{
            sql.append("order by m.init_date desc ");
        }
        sql.append("limit #{page.startIndex} , #{page.pageSize}");
        return sql.toString();
    }


    public String insertSelective(Market record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("market");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getTypeId() != null) {
            sql.VALUES("type_id", "#{typeId,jdbcType=INTEGER}");
        }
        
        if (record.getTitle() != null) {
            sql.VALUES("title", "#{title,jdbcType=VARCHAR}");
        }
        
        if (record.getDescription() != null) {
            sql.VALUES("description", "#{description,jdbcType=VARCHAR}");
        }
        
        if (record.getVariety() != null) {
            sql.VALUES("variety", "#{variety,jdbcType=VARCHAR}");
        }
        
        if (record.getWeight() != null) {
            sql.VALUES("weight", "#{weight,jdbcType=DECIMAL}");
        }
        
        if (record.getAmount() != null) {
            sql.VALUES("amount", "#{amount,jdbcType=INTEGER}");
        }
        
        if (record.getPrice() != null) {
            sql.VALUES("price", "#{price,jdbcType=DECIMAL}");
        }
        
        if (record.getPhone() != null) {
            sql.VALUES("phone", "#{phone,jdbcType=CHAR}");
        }
        
        if (record.getAddress() != null) {
            sql.VALUES("address", "#{address,jdbcType=VARCHAR}");
        }
        
        if (record.getLongitude() != null) {
            sql.VALUES("longitude", "#{longitude,jdbcType=INTEGER}");
        }
        
        if (record.getLatitude() != null) {
            sql.VALUES("latitude", "#{latitude,jdbcType=INTEGER}");
        }
        
        if (record.getAuthState() != null) {
            sql.VALUES("auth_state", "#{authState,jdbcType=INTEGER}");
        }
        
        if (record.getState() != null) {
            sql.VALUES("state", "#{state,jdbcType=INTEGER}");
        }
        
        if (record.getInitDate() != null) {
            sql.VALUES("init_date", "#{initDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getInitUser() != null) {
            sql.VALUES("init_user", "#{initUser,jdbcType=INTEGER}");
        }
        
        if (record.getModDate() != null) {
            sql.VALUES("mod_date", "#{modDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModUser() != null) {
            sql.VALUES("mod_user", "#{modUser,jdbcType=INTEGER}");
        }
        
        if (record.getAuthDate() != null) {
            sql.VALUES("auth_date", "#{authDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getAuthUser() != null) {
            sql.VALUES("auth_user", "#{authUser,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Market record) {
        SQL sql = new SQL();
        sql.UPDATE("market");
        
        if (record.getTypeId() != null) {
            sql.SET("type_id = #{typeId,jdbcType=INTEGER}");
        }
        
        if (record.getTitle() != null) {
            sql.SET("title = #{title,jdbcType=VARCHAR}");
        }
        
        if (record.getDescription() != null) {
            sql.SET("description = #{description,jdbcType=VARCHAR}");
        }
        
        if (record.getVariety() != null) {
            sql.SET("variety = #{variety,jdbcType=VARCHAR}");
        }
        
        if (record.getWeight() != null) {
            sql.SET("weight = #{weight,jdbcType=DECIMAL}");
        }
        
        if (record.getAmount() != null) {
            sql.SET("amount = #{amount,jdbcType=INTEGER}");
        }
        
        if (record.getPrice() != null) {
            sql.SET("price = #{price,jdbcType=DECIMAL}");
        }
        
        if (record.getPhone() != null) {
            sql.SET("phone = #{phone,jdbcType=CHAR}");
        }
        
        if (record.getAddress() != null) {
            sql.SET("address = #{address,jdbcType=VARCHAR}");
        }
        
        if (record.getLongitude() != null) {
            sql.SET("longitude = #{longitude,jdbcType=INTEGER}");
        }
        
        if (record.getLatitude() != null) {
            sql.SET("latitude = #{latitude,jdbcType=INTEGER}");
        }
        
        if (record.getAuthState() != null) {
            sql.SET("auth_state = #{authState,jdbcType=INTEGER}");
        }
        
        if (record.getState() != null) {
            sql.SET("state = #{state,jdbcType=INTEGER}");
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
        
        if (record.getAuthDate() != null) {
            sql.SET("auth_date = #{authDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getAuthUser() != null) {
            sql.SET("auth_user = #{authUser,jdbcType=INTEGER}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}