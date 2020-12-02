package com.demo.sheep.service;

import com.demo.sheep.constant.CommonStateEnum;
import com.demo.sheep.constant.NewsClipType;
import com.demo.sheep.constant.UploadModeEnum;
import com.demo.sheep.dao.NewsClipMapper;
import com.demo.sheep.dao.NewsMapper;
import com.demo.sheep.pojo.param.NewsClipParam;
import com.demo.sheep.pojo.param.NewsParam;
import com.demo.sheep.pojo.param.PageParam;
import com.demo.sheep.pojo.table.FileRelate;
import com.demo.sheep.pojo.table.News;
import com.demo.sheep.pojo.table.NewsClip;
import com.demo.sheep.utils.CommonUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class NewsService {

    @Resource
    private NewsMapper newsMapper;
    @Resource
    private NewsClipMapper newsClipMapper;
    @Resource
    private FileRelateService fileRelateService;

    public void save(Integer userId,NewsParam newsParam) throws ParseException {
        News news = new News();
        news.setTitle(newsParam.getTitle());
        news.setAuthor(newsParam.getAuthor());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        news.setPublishDate(sdf.parse(newsParam.getPublishDate()));
        news.setNewsSource(newsParam.getNewsSource());
        news.setInitDate(new Date());
        news.setInitUser(userId);
        news.setState(CommonStateEnum.NORMAL.code());
        newsMapper.insert(news);
        //保存片段
        List<NewsClipParam> clips = newsParam.getClips();
        NewsClip temp;
        for (int i = 0; i < clips.size(); i++) {
            NewsClipParam clip = clips.get(i);
            temp = new NewsClip();
            temp.setClipOrder(i + 1);
            temp.setNewsId(news.getId());
            if(NewsClipType.TEXT.name().equalsIgnoreCase(clip.getType())){
                temp.setClipType(clip.getType());
                temp.setContent(clip.getContent());
                newsClipMapper.insert(temp);
            }else if(NewsClipType.IMAGE.name().equalsIgnoreCase(clip.getType())){
                temp.setClipType(clip.getType());
                temp.setContent("");
                newsClipMapper.insert(temp);
                //更新和图片的关系
                fileRelateService.updateRelateId(Integer.parseInt(clip.getContent()),temp.getId());
            }
        }
    }


    public List<Map<String,Object>> list(PageParam pageParam) {
        List<News> newsList = newsMapper.queryByPage(CommonStateEnum.NORMAL.code(), pageParam);
        List<Map<String,Object>> list = new ArrayList<>(newsList.size());
        newsList.forEach(news -> {
            HashMap<String,Object> temp = new HashMap<>(2);
            temp.put("id",news.getId());
            temp.put("title",news.getTitle());
            list.add(temp);
        });
        return list;
    }

    public Map<String,Object> info(Integer newsId){
        News news = newsMapper.selectByPrimaryKey(newsId);
        if(!CommonStateEnum.NORMAL.code().equals(news.getState())){
            return null;
        }
        Map<String,Object> map = new HashMap<>();
        map.put("title",news.getTitle());
        map.put("author",news.getAuthor());
        map.put("publishDate", CommonUtil.formatDate(news.getPublishDate(),"yyyy/MM/dd HH:mm"));
        map.put("newsSource",news.getNewsSource());
        List<NewsClip> newsClips = newsClipMapper.queryByNewsId(newsId);

        List<Map<String,Object>> list = new ArrayList<>(newsClips.size());
        newsClips.forEach(clip -> {
            Map<String,Object> temp = new HashMap<>();
            String clipType = clip.getClipType();
            temp.put("clipType",clipType);
            String content = "";
            if(NewsClipType.TEXT.name().equalsIgnoreCase(clipType)){
                content = clip.getContent();
            }else if(NewsClipType.IMAGE.name().equalsIgnoreCase(clipType)){
                List<String> urls = fileRelateService.getFileUrls(UploadModeEnum.NEWS, clip.getId());
                if(!urls.isEmpty()){
                    content =urls.get(0);
                }
            }
            temp.put("content",content);
            list.add(temp);
        });
        map.put("clips",list);
        return map;
    }


}
