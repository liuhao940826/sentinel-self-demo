package com.self.sentinelbootdemo.service;

import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.self.sentinelbootdemo.utils.ExceptionUtil;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Author: liuhao
 * @Description: @SentinelResource 用于定义资源，并提供可选的异常处理和 fallback 配置项。 @SentinelResource 注解包含以下属性：
 *       fallback: fallback 函数名称，可选项，仅针对降级功能生效（DegradeException）。fallback 函数的访问范围需要是 public，
 *       参数类型和返回类型都需要与原方法相匹配，并且需要和原方法在同一个类中。业务异常不会进入 fallback 逻辑。
 *       若 blockHandler 和 fallback 都进行了配置，则遇到降级的时候首先选择 fallback 函数进行处理。
 *       注意 blockHandler 是处理被 block 的情况（所有类型的 BlockException），而 fallback 仅处理被降级的情况（DegradeException）。其它异常会原样抛出，Sentinel 不会进行处理。
 * @Date: Create in 6:06 PM 2019/3/18
 */
@Service
public class FallBackService {

    /**
     * Exception 数量的熔断
     * @param num
     * @return
     */
    @SentinelResource(value = "fallbackEC",entryType= EntryType.OUT, fallback = "FallbackEC")
    public String fallbackEC(long num){
        //自定义业务异常
        if(num%2==0){
            int i= 1/0;
        }

        return String.format("success! 数字:%d", num);
    }

    // Fallback 函数，函数签名与原函数一致.
    public String FallbackEC(long num) {
        return String.format("EC熔断:%d", num);
    }


    /**
     * Exception 概率的熔断
     * @param num
     * @return
     */
    @SentinelResource(value = "fallbackER",entryType= EntryType.OUT, fallback = "FallbackER")
    public String fallbackER(long num){
        //自定义业务异常
        if(num%2==0){
            int i= 1/0;
        }

        return String.format("success! 数字:%d", num);
    }


    // Fallback 函数，函数签名与原函数一致.
    public String FallbackER(long num) {
        return String.format("ER熔断:%d", num);
    }


    /**
     * RT 机制的熔断
     * @return
     */
    @SentinelResource(value = "fallbackRT",entryType= EntryType.OUT, fallback = "FallbackRT")
    public String fallbackRT(){
        try {
            //睡200ms
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (Exception e) {

        }

        return String.format("success!");
    }

    // Fallback 函数，函数签名与原函数一致.
    public String FallbackRT() {
        return String.format("RT熔断");
    }



}
