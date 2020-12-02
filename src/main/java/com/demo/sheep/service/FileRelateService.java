package com.demo.sheep.service;

import com.demo.sheep.constant.CommonStateEnum;
import com.demo.sheep.constant.UploadModeEnum;
import com.demo.sheep.dao.FileRelateMapper;
import com.demo.sheep.pojo.table.FileRelate;
import com.demo.sheep.utils.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class FileRelateService {
    private static final Logger log = LoggerFactory.getLogger(FileRelateService.class);

    @Value("${upload.file.path}")
    private String uploadFilePath;

    @Value("${file.host}")
    private String fileHost;

    @Resource
    private FileRelateMapper fileRelateMapper;

    public Integer save(Integer userId, MultipartFile file, UploadModeEnum uploadMode,Integer order) throws IOException {
        String filepath = CommonUtil.saveFile(uploadFilePath, uploadMode, file);
        if(filepath == null){
            return null;
        }
        //插入到数据库
        FileRelate record = new FileRelate();
        record.setInitDate(new Date());
        record.setInitUser(userId);
        record.setModeId(uploadMode.code());
        record.setOrderScore(order);
        record.setRelativePath(filepath);
        record.setState(CommonStateEnum.NORMAL.code());
        fileRelateMapper.insert(record);
        return record.getId();
    }

    public List<String> getFileUrls(UploadModeEnum uploadModeEnum,Integer relativeId){
        FileRelate relate = new FileRelate();
        relate.setState(CommonStateEnum.NORMAL.code());
        relate.setModeId(uploadModeEnum.code());
        relate.setRelateId(relativeId);
        List<String> list = fileRelateMapper.selectByModeAndRelateId(relate);
        for (int i = 0; i < list.size(); i++) {
            list.set(i,fileHost + list.get(i));
        }
        return list;
    }

    public void updateRelateId(Integer fileId,Integer relateId){
        FileRelate relate = new FileRelate();
        relate.setRelateId(relateId);
        relate.setId(fileId);
        fileRelateMapper.updateRelateId(relate);
    }




}
