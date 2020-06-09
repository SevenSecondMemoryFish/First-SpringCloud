package com.seven.second.myrule;
/// Ribbon 的自定义负载规则

import com.netflix.loadbalancer.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Ribbon的rule使用，不能在ComponentScan注解扫描包以及子包内使用
 * SpringBootApplication注解下面包含ComponentScan注解，所以rule需要和main包同级使用
 */
@Configuration
public class MySelfRule {
    @Bean
    public IRule myRule() {
        return new RandomRule();
    }
}

/**
 * 自带Rule
 * 1.RandomRule        随机
 * 2.RoundRobinRule    轮询 也是默认的
 * 3.RetryRule         先按照RoundRobinRule的策略获取服务，如果获取到的服务失败则再指定时间内进行重试
 * 4.WeightedResponseTimeRule  对RoundRobinRule扩展，响应速度快的实例选择权重越大，越容易被选择
 * 5.BestAvailableRule         会过滤掉多次访问故障而处于断路器跳闸状态的服务，然后选择一个并发量最小的服务
 * 6.AvailabilityFilteringRule 先过滤掉故障实例，再选择并发娇小的实例
 * 7.ZoneAvoidanceRule         默认规则，符合判断server所在区域的性能和server的可用性选择器
 */
