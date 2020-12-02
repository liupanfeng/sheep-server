package com.demo.sheep.controller.app;

import com.demo.sheep.constant.AuthStateEnum;
import com.demo.sheep.constant.ResultCode;
import com.demo.sheep.constant.TimeThresholdTypeEnum;
import com.demo.sheep.pojo.param.MarketParam;
import com.demo.sheep.pojo.param.PageParam;
import com.demo.sheep.pojo.view.Result;
import com.demo.sheep.service.AppHomeService;
import com.demo.sheep.service.MarketService;
import com.demo.sheep.service.NewsService;
import com.demo.sheep.service.QuotationService;
import com.demo.sheep.utils.CommonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("app")
public class AppHomeController {

    @Resource
    private AppHomeService appHomeService;
    @Resource
    private QuotationService quotationService;
    @Resource
    private MarketService marketService;
    @Resource
    private NewsService newsService;


    /**
     * 首页菜单
     */
    @RequestMapping(value = "home/type", method = RequestMethod.POST)
    @ResponseBody
    public Result homeType() {
        List<Map<String, Object>> homeTypes = appHomeService.getHomeTypes();
        return Result.success(homeTypes);
    }

    /**
     * 首页轮播图
     */
    @RequestMapping(value = "home/banner", method = RequestMethod.POST)
    @ResponseBody
    public Result homeBanner() {
        List<Map<String, Object>> homeTypes = appHomeService.getHomeBanners();
        return Result.success(homeTypes);
    }

    /**
     * 获取行情
     */
    @RequestMapping(value = "quotation/list", method = RequestMethod.POST)
    @ResponseBody
    public Result listQuotation(Integer typeId, PageParam pageParam) {
        if (typeId == null) {
            return Result.failure(ResultCode.PARAM_NOT_COMPLETE);
        }
        CommonUtil.handlePageParams(pageParam);
        List<HashMap<String, Object>> list = quotationService.appList(pageParam, typeId);
        return Result.success(list);
    }

    /**
     * 获取历史行情
     */
    @RequestMapping(value = "quotation/history", method = RequestMethod.POST)
    @ResponseBody
    public Result listQuotation(Integer id, String timeThresholdType) {
        if (id == null) {
            return Result.failure(ResultCode.PARAM_NOT_COMPLETE);
        }
        TimeThresholdTypeEnum check = TimeThresholdTypeEnum.check(timeThresholdType);
        if (check == null) {
            check = TimeThresholdTypeEnum.SEVEN;
        }
        HashMap<String, Object> map = quotationService.historyPriceForApp(id, check);
        return Result.success(map);
    }

    /**
     * 添加市场
     */
    @RequestMapping(value = "market/add", method = RequestMethod.POST)
    @ResponseBody
    public Result addMarket(HttpServletRequest request, Integer typeId, MarketParam marketParam) {
        if (typeId == null) {
            return Result.failure(ResultCode.PARAM_NOT_COMPLETE);
        }
        if (StringUtils.isBlank(marketParam.getTitle())
                || StringUtils.isBlank(marketParam.getVariety())
                || StringUtils.isBlank(marketParam.getPhone())
                || StringUtils.isBlank(marketParam.getAddress())
                || marketParam.getAmount() == null
                || marketParam.getPrice() == null
                || marketParam.getWeight() == null) {
            return Result.failure(ResultCode.PARAM_NOT_COMPLETE);
        }
        Integer userId = CommonUtil.getUserIdFromRequest(request);
        //保存到数据库
        marketService.save(userId, typeId, marketParam);
        return Result.success();
    }

    /**
     * 市场列表
     */
    @RequestMapping(value = "market/list", method = RequestMethod.POST)
    @ResponseBody
    public Result listMarket(Integer typeId, Integer listType, PageParam pageParam) {
        if (typeId == null) {
            return Result.failure(ResultCode.PARAM_NOT_COMPLETE);
        }
        CommonUtil.handlePageParams(pageParam);
        boolean isRecommend = false;
        if (listType == 2) {
            isRecommend = true;
        }
        List<Map<String, Object>> list = marketService.list(typeId, AuthStateEnum.PASS, pageParam, isRecommend);
        return Result.success(list);
    }

    /**
     * 资讯列表
     */
    @RequestMapping(value = "news/list", method = RequestMethod.POST)
    @ResponseBody
    public Result listNews(PageParam pageParam) {
        CommonUtil.handlePageParams(pageParam);
        List<Map<String, Object>> list = newsService.list(pageParam);
        return Result.success(list);
    }

    /**
     * 资讯详情
     */
    @RequestMapping(value = "news/info", method = RequestMethod.POST)
    @ResponseBody
    public Result listNews(Integer newsId) {
        if(newsId == null){
            return Result.failure(ResultCode.PARAM_NOT_COMPLETE);
        }
        Map<String, Object> info = newsService.info(newsId);
        return Result.success(info);
    }
}
