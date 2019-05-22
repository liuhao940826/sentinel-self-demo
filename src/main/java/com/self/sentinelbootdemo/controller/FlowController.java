package com.self.sentinelbootdemo.controller;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.self.sentinelbootdemo.service.FlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: liuhao
 * @Description:
 * @Date: Create in 11:11 AM 2019/3/21
 */
@RestController
public class FlowController {


    private static volatile int totalCount=0;

    @Autowired
    FlowService flowService;

    @GetMapping("/flow")
    public String  flow (){
        String result="";
        try {
            result = flowService.flow();
            return  result;
        }catch (Exception e){
            System.out.println("BlockException:"+totalCount++);
            System.out.println(e.getClass());
        }
        System.out.println(result);
        return result;

    }


    @GetMapping("/hello")
    public String  hello (long num){
        String result="";
        try {
            result = flowService.hello(num);
            return  result;
        }catch (Exception e){
            System.out.println("BlockException:"+totalCount++);
            System.out.println(e.getClass());
        }
        System.out.println(result);
        return result;

    }


}
