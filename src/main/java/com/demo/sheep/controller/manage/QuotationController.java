package com.demo.sheep.controller.manage;

import com.demo.sheep.constant.ResultCode;
import com.demo.sheep.constant.TimeThresholdTypeEnum;
import com.demo.sheep.pojo.param.PageParam;
import com.demo.sheep.pojo.param.QuotationParam;
import com.demo.sheep.pojo.view.PageView;
import com.demo.sheep.pojo.view.Result;
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

@Controller
@RequestMapping("manage")
public class QuotationController {

    @Resource
    private QuotationService quotationService;

    /**
     * 添加行情
     */
    @RequestMapping(value = "quotation/save",method = RequestMethod.POST)
    @ResponseBody
    public Result saveQuotation(QuotationParam param, HttpServletRequest request){

        Integer userId = CommonUtil.getUserIdFromRequest(request);
        if(StringUtils.isBlank(param.getName())
                || StringUtils.isBlank(param.getPlace())
                || param.getSpecification() == null
                || param.getType() == null){
            return Result.failure(ResultCode.PARAM_NOT_COMPLETE);
        }
        //保存行情
        Integer quotationId;
        if(param.getId() == 0){
            quotationId = quotationService.save(userId,param);
        }else{
            quotationService.update(userId,param);
            quotationId = param.getId();
        }
        //保存今日价格
        if(StringUtils.isNotBlank(param.getTodayPrice())){
            quotationService.updatePrice(quotationId,Double.parseDouble(param.getTodayPrice()));
        }
        return Result.success();
    }

    /**
     * 行情列表
     */
    @RequestMapping(value = "quotation/list",method = RequestMethod.POST)
    @ResponseBody
    public Result listQuotation(PageParam param,Integer type, HttpServletRequest request){
        if(type == null){
            return Result.failure(ResultCode.PARAM_NOT_COMPLETE);
        }
        CommonUtil.handlePageParams(param);
        PageView pageView = quotationService.list(param, type);
        return Result.success(pageView);
    }

    /**
     * 删除行情
     */
    @RequestMapping(value = "quotation/delete",method = RequestMethod.POST)
    @ResponseBody
    public Result deleteQuotation(QuotationParam param, HttpServletRequest request){

        Integer userId = CommonUtil.getUserIdFromRequest(request);
        if(param.getId() == null){
            return Result.failure(ResultCode.PARAM_NOT_COMPLETE);
        }
        quotationService.delete(userId,param);

        return Result.success();
    }

    /**
     * 历史行情
     */
    @RequestMapping(value = "quotation/history",method = RequestMethod.POST)
    @ResponseBody
    public Result historyQuotation(Integer id, String timeThresholdType){
        if(id == null){
            return Result.failure(ResultCode.PARAM_NOT_COMPLETE);
        }
        TimeThresholdTypeEnum check = TimeThresholdTypeEnum.check(timeThresholdType);
        if(check == null){
            check = TimeThresholdTypeEnum.SEVEN;
        }
        HashMap<String, Object> map = quotationService.historyPrice(id,check);

        return Result.success(map);
    }


}
