package com.demo.sheep;

import com.demo.sheep.service.QuotationService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;

@SpringBootTest
class SheepApplicationTests {

    @Resource
    private QuotationService quotationService;

    @Test
    void contextLoads() {
        quotationService.calculateAveragePriceOfMonth();
    }

}
