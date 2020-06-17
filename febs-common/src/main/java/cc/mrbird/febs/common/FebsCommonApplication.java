package cc.mrbird.febs.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
public class FebsCommonApplication {

    public static void main(String[] args) {
        SpringApplication.run(FebsCommonApplication.class, args);
    }

}
