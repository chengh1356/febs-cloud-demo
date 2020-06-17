package cc.mrbird.febsregister;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 开启服务注册中心
 */
@EnableEurekaServer
@SpringBootApplication
public class FebsRegisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(FebsRegisterApplication.class, args);
    }

}
