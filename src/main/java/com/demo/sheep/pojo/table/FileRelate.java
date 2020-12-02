package com.demo.sheep.pojo.table;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class FileRelate {
    private Integer id;

    private Integer modeId;

    private Integer relateId;

    private String relativePath;

    private Integer state;

    private Integer orderScore;

    private Date initDate;

    private Integer initUser;
}