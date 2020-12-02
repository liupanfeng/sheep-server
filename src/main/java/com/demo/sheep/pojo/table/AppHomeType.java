package com.demo.sheep.pojo.table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AppHomeType {
    private Integer id;

    private String name;

    private Integer level;

    private Integer parentId;

    private Integer state;

    private Integer orderIndex;
}