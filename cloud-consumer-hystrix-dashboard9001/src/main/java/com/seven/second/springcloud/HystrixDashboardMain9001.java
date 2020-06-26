package com.seven.second.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashboardMain9001 {
    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboardMain9001.class, args);
    }
}
/**
 * 1,反问的地址为http://localhost:9001/hystrix，
 * 2.在被监控的服务里面配置这样的代码，springcloud升级后，留下的坑
 *
 * @Bean public ServletRegistrationBean getServlet() {
 * HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
 * ServletRegistrationBean<HystrixMetricsStreamServlet> registrationBean = new ServletRegistrationBean<>(streamServlet);
 * registrationBean.setLoadOnStartup(1);
 * registrationBean.addUrlMappings("/hystrix.stream");
 * registrationBean.setName("HystrixMetricsStreamServlet");
 * return  registrationBean;
 * }
 * <p>
 * 3，在设置监听服务的地址，比如:http://localhost:8001/hystrix.stream,
 * localhost:8001:标识监听服务的域名以及端口号
 * 并且设置下延迟的时间，以及服务的名称即可添加今天
 */
