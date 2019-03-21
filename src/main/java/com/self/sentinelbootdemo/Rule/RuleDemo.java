//package com.self.sentinelbootdemo.Rule;
//
//import com.alibaba.csp.sentinel.slots.block.RuleConstant;
//import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
//import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
//import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
//import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
//import com.alibaba.csp.sentinel.slots.system.SystemRule;
//import com.alibaba.csp.sentinel.slots.system.SystemRuleManager;
//import org.springframework.beans.factory.InitializingBean;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @Author: liuhao
// * @Description:
// * @Date: Create in 5:09 PM 2019/3/19
// */
//public class RuleDemo implements InitializingBean {
//
//    /**
//     * 流量控制规则
//     */
//    private void initFlowQpsRule() {
//        List<FlowRule> rules = new ArrayList<>();
//        FlowRule rule = new FlowRule("flowRole");
//        // set limit qps to 20
//        rule.setCount(1);
//        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
//        rule.setLimitApp("default");
//        rules.add(rule);
//        FlowRuleManager.loadRules(rules);
//    }
//
//
//    /**
//     * 熔断降级规则 同一个资源可以同时有多个降级规则
//     */
//    private void initDegradeRule2() {
//        List<DegradeRule> rules = new ArrayList<>();
//        DegradeRule rule = new DegradeRule();
//        rule.setResource("flowRole");
//        // set threshold RT, 10 ms
//        rule.setCount(1);//阈值
//        rule.setGrade(RuleConstant.DEGRADE_GRADE_RT);//降级模式，根据 RT 降级还是根据异常比例降级 默认RT
//        rule.setTimeWindow(10);//降级的时间，单位为 s
//        rules.add(rule);
//        DegradeRuleManager.loadRules(rules);
//    }
//
//    /**
//     * 熔断降级规则 同一个资源可以同时有多个降级规则
//     */
//    private void initDegradeRule() {
//        List<DegradeRule> rules = new ArrayList<>();
//        DegradeRule rule = new DegradeRule();
//        rule.setResource("degradeRule");
//        // set threshold RT, 10 ms
//        rule.setCount(1);//阈值
//        rule.setGrade(RuleConstant.DEGRADE_GRADE_RT);//降级模式，根据 RT 降级还是根据异常比例降级 默认RT
//        rule.setTimeWindow(10);//降级的时间，单位为 s
//        rules.add(rule);
//        DegradeRuleManager.loadRules(rules);
//    }
//
//    /**
//     * 系统保护规则 -1 (不生效)
//     */
//    private void initSystemRule() {
//        List<SystemRule> rules = new ArrayList<>();
//        SystemRule rule = new SystemRule();
//        rule.setHighestSystemLoad(10);//最大的 load1，参考值
//        rules.add(rule);
//        SystemRuleManager.loadRules(rules);
//    }
//
//    /**
//     * spring 初始化时候执行加载这样的规则
//     * @throws Exception
//     */
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        initFlowQpsRule();
//        initDegradeRule();
//        initDegradeRule2();
//        initSystemRule();
//
//    }
//}
