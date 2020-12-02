package com.demo.sheep.service;

import com.demo.sheep.constant.CommonStateEnum;
import com.demo.sheep.constant.TimeThresholdTypeEnum;
import com.demo.sheep.dao.QuotationDayRecordMapper;
import com.demo.sheep.dao.QuotationMapper;
import com.demo.sheep.dao.QuotationMonthRecordMapper;
import com.demo.sheep.pojo.param.PageParam;
import com.demo.sheep.pojo.param.QuotationParam;
import com.demo.sheep.pojo.table.Quotation;
import com.demo.sheep.pojo.table.QuotationDayRecord;
import com.demo.sheep.pojo.table.QuotationMonthRecord;
import com.demo.sheep.pojo.view.PageView;
import com.demo.sheep.utils.CommonUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

@Service
public class QuotationService {

    @Resource
    private QuotationMapper quotationMapper;
    @Resource
    private QuotationDayRecordMapper quotationDayRecordMapper;
    @Resource
    private QuotationMonthRecordMapper quotationMonthRecordMapper;

    public Integer save(Integer userId, QuotationParam param) {
        Quotation record = new Quotation();
        record.setInitDate(new Date());
        record.setModDate(new Date());
        record.setInitUser(userId);
        record.setModUser(userId);
        record.setName(param.getName());
        record.setPlace(param.getPlace());
        record.setSpecification(param.getSpecification());
        record.setState(CommonStateEnum.NORMAL.code());
        record.setTypeId(param.getType());
        //插入到数据库
        quotationMapper.insert(record);
        return record.getId();
    }

    public void update(Integer userId, QuotationParam param) {
        Quotation record = new Quotation();
        record.setId(param.getId());
        record.setModDate(new Date());
        record.setModUser(userId);
        record.setName(param.getName());
        record.setPlace(param.getPlace());
        record.setSpecification(param.getSpecification());
        record.setState(CommonStateEnum.NORMAL.code());
        //插入到数据库
        quotationMapper.updateByPrimaryKeySelective(record);
    }

    public void delete(Integer userId, QuotationParam param) {
        Quotation record = new Quotation();
        record.setModDate(new Date());
        record.setModUser(userId);
        record.setId(param.getId());
        record.setState(CommonStateEnum.DELETE.code());
        //更新数据库结构
        quotationMapper.updateStateById(record);
    }

    //分页数据返回
    public PageView list(PageParam param, Integer type) {
        Integer count = quotationMapper.queryCount(type, CommonStateEnum.NORMAL.code());
        if (count == 0) {
            return CommonUtil.emptyPageView();
        }
        PageView pageView = new PageView();
        pageView.setCount(count);
        String today = CommonUtil.formatDate(new Date(), "yyyy-MM-dd");
        List<HashMap<String, Object>> query = quotationMapper.queryByPage(param, type, CommonStateEnum.NORMAL.code(), today);
        query.forEach(quotation -> {
            quotation.put("todayPrice", quotation.remove("total_price"));
            quotation.put("modDate", CommonUtil.formatDate((Date) quotation.remove("mod_date"), "yyyy-MM-dd HH:mm:ss"));
        });
        pageView.setElements(query);

        return pageView;
    }

    /**
     * 更新行情价格
     */
    public void updatePrice(Integer quotationId, Double price) {
        String initDay = CommonUtil.formatDate(new Date(), "yyyy-MM-dd");
        //查询是否有记录
        QuotationDayRecord record = quotationDayRecordMapper.selectByQuotationIdAndInitDay(quotationId, initDay);
        Quotation quotation = quotationMapper.selectByPrimaryKey(quotationId);
        if (record == null) {
            record = new QuotationDayRecord();
            record.setInitDay(initDay);
            record.setTotalPrice(price);
            record.setAveragePrice(CommonUtil.get2ScaleOfDouble(price / quotation.getSpecification()));
            record.setQuotationId(quotationId);
            quotationDayRecordMapper.insert(record);
        } else {
            record.setTotalPrice(price);
            record.setAveragePrice(CommonUtil.get2ScaleOfDouble(price / quotation.getSpecification()));
            quotationDayRecordMapper.updatePriceByPrimaryKey(record);
        }
    }

    /**
     * 查询行情历史价格
     */
    public HashMap<String, Object> historyPrice(Integer quotationId, TimeThresholdTypeEnum timeThreshold) {
        //查询是否有记录
        List<HashMap<String,Number>> query = quotationDayRecordMapper.historyPrice(quotationId, timeThreshold.value());
        List<String> initDates = new ArrayList<>(query.size());
        List<Double> prices = new ArrayList<>(query.size());
        for (int i = query.size() - 1; i > -1; i--) {
            initDates.add(String.valueOf(query.get(i).get("init_day")));
            prices.add(query.get(i).get("average_price").doubleValue());
        }
        HashMap<String, Object> result = new HashMap<>(2);
        result.put("dates", initDates);
        result.put("prices", prices);
        return result;
    }

    //分页数据返回
    public List<HashMap<String, Object>> appList(PageParam param, Integer type) {
        String today = CommonUtil.formatDate(new Date(), "yyyy-MM-dd");
        List<HashMap<String, Object>> query = quotationMapper.queryByPage(param, type, CommonStateEnum.NORMAL.code(), today);
        query.forEach(quotation -> {
            Object totalPrice = quotation.remove("total_price");
            quotation.put("todayPrice", totalPrice == null ? 0.0 : totalPrice);
            quotation.remove("mod_date");
        });
        return query;
    }

    /**
     * 计算当月的行请平均价格
     */
    public void calculateAveragePriceOfMonth() {
        int pageNum = 1;
        Integer pageSize = 100;
        List<Quotation> list;
        String initMonth = CommonUtil.formatDate(new Date(), "yyyy-MM");
        while (true) {
            list = quotationMapper.selectByPage(CommonStateEnum.NORMAL.code(), (pageNum - 1) * pageSize, pageSize);
            if (list.isEmpty()) {
                break;
            }
            for (Quotation quotation : list) {
                Integer id = quotation.getId();
                HashMap<String, Object> map = quotationDayRecordMapper.selectByQuotationIdAndInitDayByLike(id, initMonth + "___");
                BigDecimal total = (BigDecimal) map.get("total");
                Long num = (Long) map.get("num");
                if (total == null || num == 0) {
                    continue;
                }
                //查询是否已有记录
                QuotationMonthRecord monthRecord = quotationMonthRecordMapper.selectByQuotationIdAndMonth(id, initMonth);
                if (monthRecord == null) {
                    monthRecord = new QuotationMonthRecord();
                    monthRecord.setInitMonth(initMonth);
                    monthRecord.setQuotationId(id);
                    monthRecord.setAveragePrice(CommonUtil.get2ScaleOfDouble(total.doubleValue() / num));
                    quotationMonthRecordMapper.insert(monthRecord);
                } else {
                    monthRecord.setAveragePrice(CommonUtil.get2ScaleOfDouble(total.doubleValue() / num));
                    quotationMonthRecordMapper.updatePriceByPrimaryKey(monthRecord);
                }
            }
            pageNum++;
        }

    }

    /**
     * 返回给app的用于查询行情的历史
     * @return
     */
    public HashMap<String, Object> historyPriceForApp(Integer quotationId, TimeThresholdTypeEnum timeThreshold) {
        //查询最近的记录
        List<HashMap<String,Number>> query = quotationDayRecordMapper.historyPrice(quotationId, timeThreshold.value());
        List<String> initDates = new ArrayList<>(query.size());
        List<Double> prices = new ArrayList<>(query.size());
        for (int i = query.size() - 1; i > -1; i--) {
            initDates.add(String.valueOf(query.get(i).get("init_day")));
            prices.add(query.get(i).get("average_price").doubleValue());
        }
        HashMap<String, Object> history = new HashMap<>(2);
        HashMap<String, Object> statistics = new HashMap<>(2);
        HashMap<String, Object> result = new HashMap<>(2);
        result.put("history",history);
        result.put("statistics",statistics);
        //展示图
        history.put("dates", initDates);
        history.put("prices", prices);
        //统计信息
        double today = 0.00;
        double yesterday = 0.00;
        double max = 0.00;
        double min = Integer.MAX_VALUE;
        double average = 0.00;
        if(!prices.isEmpty()){
            List<Double> recentPrice = prices.subList(0, prices.size() > 7 ? 6 : prices.size() - 1);
            today = recentPrice.get(0);
            double sum = 0.00;
            for (int i = 0; i < recentPrice.size(); i++) {
                double temp = recentPrice.get(i);
                if(i == 1){
                    yesterday = temp;
                }
                sum += temp;
                if(max < temp){
                    max = temp;
                }
                if(min > temp){
                    min = temp;
                }
            }
            average = sum / recentPrice.size();

        }
        statistics.put("today",today);
        statistics.put("yesterday",yesterday);
        statistics.put("max",max);
        statistics.put("min",min);
        statistics.put("average",average);

        return result;
    }


}
