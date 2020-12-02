package com.demo.sheep.dao;

import com.demo.sheep.pojo.table.FileRelate;
import org.apache.ibatis.jdbc.SQL;

public class FileRelateSqlProvider {

    public String insertSelective(FileRelate record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("file_relate");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getModeId() != null) {
            sql.VALUES("mode_id", "#{modeId,jdbcType=INTEGER}");
        }
        
        if (record.getRelateId() != null) {
            sql.VALUES("relate_id", "#{relateId,jdbcType=INTEGER}");
        }
        
        if (record.getRelativePath() != null) {
            sql.VALUES("relative_path", "#{relativePath,jdbcType=VARCHAR}");
        }
        
        if (record.getState() != null) {
            sql.VALUES("state", "#{state,jdbcType=INTEGER}");
        }
        
        if (record.getOrderScore() != null) {
            sql.VALUES("order_score", "#{orderScore,jdbcType=INTEGER}");
        }
        
        if (record.getInitDate() != null) {
            sql.VALUES("init_date", "#{initDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getInitUser() != null) {
            sql.VALUES("init_user", "#{initUser,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(FileRelate record) {
        SQL sql = new SQL();
        sql.UPDATE("file_relate");
        
        if (record.getModeId() != null) {
            sql.SET("mode_id = #{modeId,jdbcType=INTEGER}");
        }
        
        if (record.getRelateId() != null) {
            sql.SET("relate_id = #{relateId,jdbcType=INTEGER}");
        }
        
        if (record.getRelativePath() != null) {
            sql.SET("relative_path = #{relativePath,jdbcType=VARCHAR}");
        }
        
        if (record.getState() != null) {
            sql.SET("state = #{state,jdbcType=INTEGER}");
        }
        
        if (record.getOrderScore() != null) {
            sql.SET("order_score = #{orderScore,jdbcType=INTEGER}");
        }
        
        if (record.getInitDate() != null) {
            sql.SET("init_date = #{initDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getInitUser() != null) {
            sql.SET("init_user = #{initUser,jdbcType=INTEGER}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}