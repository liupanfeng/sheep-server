package com.demo.sheep.dao;

import com.demo.sheep.pojo.table.NewsClip;
import org.apache.ibatis.jdbc.SQL;

public class NewsClipSqlProvider {

    public String insertSelective(NewsClip record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("news_clip");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getNewsId() != null) {
            sql.VALUES("news_id", "#{newsId,jdbcType=INTEGER}");
        }
        
        if (record.getClipType() != null) {
            sql.VALUES("clip_type", "#{clipType,jdbcType=VARCHAR}");
        }
        
        if (record.getContent() != null) {
            sql.VALUES("content", "#{content,jdbcType=VARCHAR}");
        }
        
        if (record.getClipOrder() != null) {
            sql.VALUES("clip_order", "#{clipOrder,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(NewsClip record) {
        SQL sql = new SQL();
        sql.UPDATE("news_clip");
        
        if (record.getNewsId() != null) {
            sql.SET("news_id = #{newsId,jdbcType=INTEGER}");
        }
        
        if (record.getClipType() != null) {
            sql.SET("clip_type = #{clipType,jdbcType=VARCHAR}");
        }
        
        if (record.getContent() != null) {
            sql.SET("content = #{content,jdbcType=VARCHAR}");
        }
        
        if (record.getClipOrder() != null) {
            sql.SET("clip_order = #{clipOrder,jdbcType=INTEGER}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}