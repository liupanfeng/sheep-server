package com.demo.sheep.service;

import com.demo.sheep.constant.CommonStateEnum;
import com.demo.sheep.constant.UploadModeEnum;
import com.demo.sheep.dao.AppHomeBannerMapper;
import com.demo.sheep.dao.AppHomeTypeMapper;
import com.demo.sheep.pojo.table.AppHomeBanner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class AppHomeService {

    @Resource
    private AppHomeTypeMapper appHomeTypeMapper;
    @Resource
    private AppHomeBannerMapper appHomeBannerMapper;
    @Resource
    private FileRelateService fileRelateService;


    public List<Map<String, Object>> getHomeTypes() {
        List<Map<String, Object>> homeTypes = appHomeTypeMapper.selectByParentId(0, CommonStateEnum.NORMAL.code());
        homeTypes.forEach(type -> {
            List<Map<String, Object>> maps = appHomeTypeMapper.selectByParentId((Integer) type.get("id"), CommonStateEnum.NORMAL.code());
            type.put("children", maps);
        });
        return homeTypes;
    }

    public List<Map<String, Object>> getHomeBanners() {
        List<Map<String, Object>> banners = appHomeBannerMapper.queryForHome();
        banners.forEach(banner -> {
            List<String> urls = fileRelateService.getFileUrls(UploadModeEnum.BANNER, (Integer) banner.remove("id"));
            if(!urls.isEmpty()){
                banner.put("picUrl",urls.get(0));
            }
        });
        return banners;
    }
}
