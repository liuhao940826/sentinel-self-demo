package com.self.sentinelbootdemo.controller;

import com.self.sentinelbootdemo.service.FallBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: liuhao
 * @Description:
 * @Date: Create in 11:39 AM 2019/3/20
 */
@RestController
public class FallBackController {

    @Autowired
    private FallBackService fallbackservice;

    private static volatile int totalCount=0;
    private static volatile int degradeCount=0;
    private static volatile int flockCount=0;
    private static volatile int sysCount=0;


    @GetMapping("/fallbackEC")
    public String fallbackEC(long num) {

        long startTime = System.currentTimeMillis();

        String result = fallbackservice.fallbackEC(num);

        long endTime = System.currentTimeMillis();

        System.out.println("执行时间:" + (endTime - startTime)+"result:"+result);

        return result;
    }


    @GetMapping("/fallbackER")
    public String fallbackER(long num) {
        long startTime = System.currentTimeMillis();

        String result = fallbackservice.fallbackER(num);

        long endTime = System.currentTimeMillis();

        System.out.println("执行时间:" + (endTime - startTime)+"result:"+result);

        return result;
    }


    @GetMapping("/fallbackRT")
    public String fallbackRT() {
        long startTime = System.currentTimeMillis();

        String result = fallbackservice.fallbackRT();

        long endTime = System.currentTimeMillis();

        System.out.println("执行时间:" + (endTime - startTime)+"result:"+result);

        return result;
    }

}
