package com.demo.sheep.pojo.param;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UploadFileParam {

    private Integer uploadMode;
    private MultipartFile file;
    private Integer order;


}
