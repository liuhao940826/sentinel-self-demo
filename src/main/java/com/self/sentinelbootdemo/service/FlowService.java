package com.self.sentinelbootdemo.service;

import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.self.sentinelbootdemo.utils.ExceptionUtil;
import org.springframework.stereotype.Service;

/**
 * @Author: liuhao
 * @Description:
 * @Date: Create in 6:06 PM 2019/3/18
 */
@Service
public class FlowService {

    /**
     * @SentinelResource 用于定义资源，并提供可选的异常处理和 fallback 配置项。 @SentinelResource 注解包含以下属性：
     * value: 资源名称，必需项（不能为空）
     * entryType: 入口类型，可选项（默认为 EntryType.OUT）
     * blockHandler / blockHandlerClass: blockHandler 对应处理 BlockException 的函数名称，可选项。若未配置，则将 BlockException 直接抛出。blockHandler 函数访问范围需要是 public，返回类型需要与原方法相匹配，参数类型需要和原方法相匹配并且最后加一个额外的参数，类型为 BlockException。blockHandler 函数默认需要和原方法在同一个类中。若希望使用其他类的函数，则可以指定 blockHandlerClass 为对应的类的 Class 对象，注意对应的函数必需为 static 函数，否则无法解析。
     * fallback: fallback 函数名称，可选项，仅针对降级功能生效（DegradeException）。fallback 函数的访问范围需要是 public，参数类型和返回类型都需要与原方法相匹配，并且需要和原方法在同一个类中。业务异常不会进入 fallback 逻辑。
     * 若 blockHandler 和 fallback 都进行了配置，则遇到降级的时候首先选择 fallback 函数进行处理。
     *
     * 注意 blockHandler 是处理被 block 的情况（所有类型的 BlockException），而 fallback 仅处理被降级的情况（DegradeException）。其它异常会原样抛出，Sentinel 不会进行处理。
     *
     * 注意 blockHandler 函数会在原方法被限流/降级/系统保护的时候调用，
     * 而 fallback 函数仅会在原方法被降级时作为 fallback 方法，其余时候不会被调用。
     *
     */

    // 对应的 `handleException` 函数需要位于 `ExceptionUtil` 类中，并且必须为 static 函数.
    @SentinelResource(value = "flow", blockHandler = "handleException", blockHandlerClass = {ExceptionUtil.class})
    public String flow() {
        System.out.println("success");
        return "success";

    }

    // 原函数
    @SentinelResource(value = "hello", blockHandler = "exceptionHandler", fallback = "helloFallback")
    public String hello(long s) {
        return String.format("Hello at %d", s);
    }

    // Fallback 函数，函数签名与原函数一致.
    public String helloFallback(long s) {
        return String.format("Halooooo %d", s);
    }

    // Block 异常处理函数，参数最后多一个 BlockException，其余与原函数一致.
    public String exceptionHandler(long s, BlockException ex) {
        // Do some log here.
        ex.printStackTrace();
        return "Oops, error occurred at " + s;
    }


}
