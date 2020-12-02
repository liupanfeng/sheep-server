package com.demo.sheep.pojo.param;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NewsParam {

    private String title;
    private String author;
    private String publishDate;
    private String newsSource;
    private List<NewsClipParam> clips;

}
