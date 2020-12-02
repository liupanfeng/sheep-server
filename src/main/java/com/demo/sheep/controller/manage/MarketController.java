package com.demo.sheep.controller.manage;

import com.demo.sheep.constant.AuthStateEnum;
import com.demo.sheep.constant.ResultCode;
import com.demo.sheep.constant.TimeThresholdTypeEnum;
import com.demo.sheep.pojo.param.PageParam;
import com.demo.sheep.pojo.param.QuotationParam;
import com.demo.sheep.pojo.view.PageView;
import com.demo.sheep.pojo.view.Result;
import com.demo.sheep.service.MarketService;
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
@RequestMapping("manage")
public class MarketController {

    @Resource
    private MarketService marketService;

    /**
     * 市场列表
     */
    @RequestMapping(value = "market/list", method = RequestMethod.POST)
    @ResponseBody
    public Result listMarket(HttpServletRequest request, Integer typeId, Integer authState, PageParam pageParam) {
        if (typeId == null || authState == null) {
            return Result.failure(ResultCode.PARAM_NOT_COMPLETE);
        }
        CommonUtil.handlePageParams(pageParam);
        boolean isRecommend;
        AuthStateEnum check;
        if(authState == 2){
            isRecommend = true;
            check = AuthStateEnum.PASS;
        }else{
            isRecommend = false;
            check = AuthStateEnum.check(authState);
            if (check == null) {
                check = AuthStateEnum.PASS;
            }
        }
        List<Map<String, Object>> list = marketService.list(typeId, check, pageParam, isRecommend);
        return Result.success(list);
    }

    /**
     * 审核
     */
    @RequestMapping(value = "market/auth", method = RequestMethod.POST)
    @ResponseBody
    public Result authMarket(HttpServletRequest request, Integer marketId,Integer authState) {
        if (marketId == null || authState == null) {
            return Result.failure(ResultCode.PARAM_NOT_COMPLETE);
        }
        Integer userId = CommonUtil.getUserIdFromRequest(request);
        AuthStateEnum check = AuthStateEnum.check(authState);
        if(check == null){
            return Result.failure(ResultCode.PARAM_IS_INVALID);
        }
        marketService.auth(userId, marketId,authState);
        return Result.success();
    }

    /**
     * 删除
     */
    @RequestMapping(value = "market/delete", method = RequestMethod.POST)
    @ResponseBody
    public Result deleteMarket(HttpServletRequest request, Integer marketId) {
        if (marketId == null) {
            return Result.failure(ResultCode.PARAM_NOT_COMPLETE);
        }
        Integer userId = CommonUtil.getUserIdFromRequest(request);

        marketService.delete(userId, marketId);
        return Result.success();
    }

    /**
     * 添加到推荐
     */
    @RequestMapping(value = "market/recommend/add", method = RequestMethod.POST)
    @ResponseBody
    public Result addRecommend(HttpServletRequest request, Integer typeId, Integer marketId) {
        if (marketId == null || typeId == null) {
            return Result.failure(ResultCode.PARAM_NOT_COMPLETE);
        }
        Integer userId = CommonUtil.getUserIdFromRequest(request);

        marketService.addToRecommend(userId, typeId, marketId);
        return Result.success();
    }

    /**
     * 从推荐移除
     */
    @RequestMapping(value = "market/recommend/remove", method = RequestMethod.POST)
    @ResponseBody
    public Result removeRecommend(HttpServletRequest request, Integer marketId) {
        if (marketId == null) {
            return Result.failure(ResultCode.PARAM_NOT_COMPLETE);
        }
        marketService.removeFromRecommend(marketId);
        return Result.success();
    }


}
