package com.demo.sheep.dao;

import com.demo.sheep.pojo.table.News;
import org.apache.ibatis.jdbc.SQL;

public class NewsSqlProvider {

    public String insertSelective(News record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("news");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getTitle() != null) {
            sql.VALUES("title", "#{title,jdbcType=VARCHAR}");
        }
        
        if (record.getAuthor() != null) {
            sql.VALUES("author", "#{author,jdbcType=VARCHAR}");
        }
        
        if (record.getPublishDate() != null) {
            sql.VALUES("publish_date", "#{publishDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getNewsSource() != null) {
            sql.VALUES("news_source", "#{newsSource,jdbcType=VARCHAR}");
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
        
        if (record.getState() != null) {
            sql.VALUES("state", "#{state,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(News record) {
        SQL sql = new SQL();
        sql.UPDATE("news");
        
        if (record.getTitle() != null) {
            sql.SET("title = #{title,jdbcType=VARCHAR}");
        }
        
        if (record.getAuthor() != null) {
            sql.SET("author = #{author,jdbcType=VARCHAR}");
        }
        
        if (record.getPublishDate() != null) {
            sql.SET("publish_date = #{publishDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getNewsSource() != null) {
            sql.SET("news_source = #{newsSource,jdbcType=VARCHAR}");
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
            sql.SET("state = #{state,jdbcType=INTEGER}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}