package com.demo.sheep.schedule;

import com.demo.sheep.service.QuotationService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class QuotationSchedule {

    @Resource
    private QuotationService quotationService;

    /**
     * 每天晚上11点30跑当月的行情平均价格
     */
    @Scheduled(cron = "0 9 23 * * ?")
    public void runAverageMonthPrice(){
        quotationService.calculateAveragePriceOfMonth();
    }

}
