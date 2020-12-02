package com.demo.sheep.service;

import com.demo.sheep.constant.AuthStateEnum;
import com.demo.sheep.constant.CommonStateEnum;
import com.demo.sheep.constant.UploadModeEnum;
import com.demo.sheep.dao.FileRelateMapper;
import com.demo.sheep.dao.MarketMapper;
import com.demo.sheep.dao.MarketRecommendMapper;
import com.demo.sheep.pojo.param.MarketParam;
import com.demo.sheep.pojo.param.PageParam;
import com.demo.sheep.pojo.table.FileRelate;
import com.demo.sheep.pojo.table.Market;
import com.demo.sheep.pojo.table.MarketRecommend;
import com.demo.sheep.utils.CommonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class MarketService {
    @Resource
    private MarketMapper marketMapper;

    @Resource
    private MarketRecommendMapper marketRecommendMapper;

    @Resource
    private FileRelateMapper fileRelateMapper;

    @Value("${file.host}")
    private String fileHost;

    public void save(Integer userId, Integer typeId, MarketParam marketParam) {
        Market record = new Market();
        BeanUtils.copyProperties(marketParam, record);
        record.setInitUser(userId);
        record.setTypeId(typeId);
        record.setInitDate(new Date());
        record.setAuthState(AuthStateEnum.WAIT.code());
        record.setState(CommonStateEnum.NORMAL.code());
        marketMapper.insert(record);
        //关联图片
        if (StringUtils.isNotBlank(marketParam.getFileIds())) {
            FileRelate relate = new FileRelate();
            relate.setRelateId(record.getId());
            String[] split = marketParam.getFileIds().split(",");
            for (String fileId : split) {
                relate.setId(Integer.parseInt(fileId));
                fileRelateMapper.updateRelateId(relate);
            }
        }
    }

    public List<Map<String, Object>> list(Integer typeId, AuthStateEnum authState, PageParam pageParam, boolean isRecommend) {
        Market record = new Market();
        record.setTypeId(typeId);
        record.setAuthState(authState.code());
        record.setState(CommonStateEnum.NORMAL.code());

        List<Map<String, Object>> markets = marketMapper.selectByTypeIdAndAuthState(record, pageParam, isRecommend);

        markets.forEach(market -> {
            market.put("authState", market.remove("auth_state"));
            market.put("initDate", CommonUtil.formatDate((Date) market.remove("init_date"), "yyyy-MM-dd HH:mm:ss"));
            //查询相关联的图片
            FileRelate relate = new FileRelate();
            relate.setModeId(UploadModeEnum.MARKET.code());
            relate.setRelateId((Integer) market.get("id"));
            relate.setState(CommonStateEnum.NORMAL.code());
            List<String> fileRelates = fileRelateMapper.selectByModeAndRelateId(relate);
            List<String> list = new ArrayList<>(fileRelates.size());
            fileRelates.forEach(path -> list.add(fileHost + path));
            market.put("images", list);
        });
        return markets;
    }

    public void auth(Integer userId, Integer marketId, Integer authState) {
        Market market = new Market();
        market.setAuthState(authState);
        market.setAuthDate(new Date());
        market.setAuthUser(userId);
        market.setId(marketId);
        marketMapper.updateAuthInfo(market);
    }

    public void delete(Integer userId, Integer marketId) {
        Market market = new Market();
        market.setState(CommonStateEnum.DELETE.code());
        market.setModDate(new Date());
        market.setModUser(userId);
        market.setId(marketId);
        marketMapper.updateState(market);
    }

    /**
     * 添加到推荐列表
     *
     * @param userId
     * @param typeId
     * @param marketId
     */
    public void addToRecommend(Integer userId, Integer typeId, Integer marketId) {
        //判断是否已经推荐了
        Integer exists = marketRecommendMapper.selectByMarketId(marketId);
        if(exists != null){
            return;
        }
        MarketRecommend recommend = new MarketRecommend();
        recommend.setMarketId(marketId);
        recommend.setInitDate(new Date());
        recommend.setInitUser(userId);
        recommend.setOrderScore(1);
        recommend.setTypeId(typeId);
        marketRecommendMapper.insert(recommend);
    }

    /**
     * 从推荐列表移除
     */
    public void removeFromRecommend(Integer marketId) {
        marketRecommendMapper.deleteByMarketId(marketId);
    }

}
