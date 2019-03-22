package com.self.sentinelbootdemo.controller;

import com.dadaabc.service.common.web.utils.HttpUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author: liuhao
 * @Description:
 * @Date: Create in 3:39 PM 2019/3/20
 */
@RestController
public class SchedulingController {

    @GetMapping("/schedulingFlow")
    public String scheduling(Integer nums){


        Executor executor = Executors.newFixedThreadPool(nums, (Runnable r) -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        });

        List<CompletableFuture<Void>> futures =new ArrayList<>();

        for (int i = 0; i < nums; i++) {
            CompletableFuture<Void> future=CompletableFuture.runAsync(() ->
                    System.out.println(HttpUtils.sendGet("http://127.0.0.1:7000/flow")), executor);
            futures.add(future);
        }

        return "ok";
    }

    @GetMapping("/schedulingFallBackRT")
    public String fallBackRT(Integer nums){

//        Executor executor = Executors.newFixedThreadPool(nums, (Runnable r) -> {
//            Thread t = new Thread(r);
//            t.setDaemon(true);
//            return t;
//        });
////
////        List<CompletableFuture<Void>> futures =new ArrayList<>();
////
////        for (int i = 0; i < nums; i++) {
////            CompletableFuture<Void> future;
////            if(i%2==0){
////                future  =CompletableFuture.runAsync(() ->
////                        HttpUtils.sendGet("http://127.0.0.1:7000//fallback?num=2"), executor);
////            }else {
////                future  =CompletableFuture.runAsync(() ->
////                        HttpUtils.sendGet("http://127.0.0.1:7000//fallback?num=1"), executor);
////            }
////
////            futures.add(future);
////        }
////
////        return "ok";
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(100,100,0,
                TimeUnit.MINUTES,new LinkedBlockingQueue<>());


        for (int i = 0; i < nums; i++) {
            threadPoolExecutor.execute(()->{
                String resp = HttpUtils.sendGet("http://127.0.0.1:7000/fallbackRT");
                System.out.println(resp);
            });

        }

        return "ok";
    }

    @GetMapping("/schedulingFallBackEC")
    public String fallbackEC(Integer nums){

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(100,100,0,
                TimeUnit.MINUTES,new LinkedBlockingQueue<>());

        for (int i = 0; i < nums; i++) {
            threadPoolExecutor.execute(()->{
                String resp = HttpUtils.sendGet("http://127.0.0.1:7000/fallbackEC?num=2");
                System.out.println(resp);
            });

        }

        return "ok";
    }


    @GetMapping("/schedulingFallBackER")
    public String fallbackER(Integer nums){

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(100,100,0,
                TimeUnit.MINUTES,new LinkedBlockingQueue<>());

        for (int i = 0; i < nums; i++) {
            threadPoolExecutor.execute(()->{
                String resp = HttpUtils.sendGet("http://127.0.0.1:7000/fallbackER?num=2");
                System.out.println(resp);
            });

        }

        return "ok";
    }

}
