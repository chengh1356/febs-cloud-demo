package cc.mrbird.febs.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 网关服务器
 */
@EnableZuulProxy        //开启Zuul服务网关功能
@EnableDiscoveryClient  //注册到注册中心
@SpringBootApplication
public class FebsGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(FebsGatewayApplication.class, args);
    }

}
