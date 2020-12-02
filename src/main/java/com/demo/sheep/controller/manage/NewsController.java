package com.demo.sheep.controller.manage;

import com.demo.sheep.constant.ResultCode;
import com.demo.sheep.constant.TimeThresholdTypeEnum;
import com.demo.sheep.pojo.param.NewsParam;
import com.demo.sheep.pojo.param.PageParam;
import com.demo.sheep.pojo.param.QuotationParam;
import com.demo.sheep.pojo.view.PageView;
import com.demo.sheep.pojo.view.Result;
import com.demo.sheep.service.NewsService;
import com.demo.sheep.service.QuotationService;
import com.demo.sheep.utils.CommonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.HashMap;

@Controller
@RequestMapping("manage")
public class NewsController {

    @Resource
    private NewsService newsService;

    /**
     * 保存资讯
     */
    @RequestMapping(value = "news/save",method = RequestMethod.POST)
    @ResponseBody
    public Result saveQuotation(@RequestBody NewsParam newsParam, HttpServletRequest request) throws ParseException {

        Integer userId = CommonUtil.getUserIdFromRequest(request);
        newsService.save(userId,newsParam);

        return Result.success();
    }

    /**
     * 行情列表
     */
    @RequestMapping(value = "news/list",method = RequestMethod.POST)
    @ResponseBody
    public Result listQuotation(PageParam param,Integer type, HttpServletRequest request){
        if(type == null){
            return Result.failure(ResultCode.PARAM_NOT_COMPLETE);
        }
        CommonUtil.handlePageParams(param);
        //PageView pageView = quotationService.list(param, type);
        return Result.success();
    }

    /**
     * 删除行情
     */
    @RequestMapping(value = "news/delete",method = RequestMethod.POST)
    @ResponseBody
    public Result deleteQuotation(QuotationParam param, HttpServletRequest request){

        Integer userId = CommonUtil.getUserIdFromRequest(request);
        if(param.getId() == null){
            return Result.failure(ResultCode.PARAM_NOT_COMPLETE);
        }
        //quotationService.delete(userId,param);

        return Result.success();
    }



}
