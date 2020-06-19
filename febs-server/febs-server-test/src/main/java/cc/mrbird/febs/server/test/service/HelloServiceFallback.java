package cc.mrbird.febs.server.test.service;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Description 回退方法
 * @Author 54045
 * @Date 2020-06-20 9:00
 */
@Slf4j
@Component
public class HelloServiceFallback implements FallbackFactory<IHelloService> {
    @Override
    public IHelloService create(Throwable throwable) {
        return name -> {
                log.error("调用febs-server-system服务出错",throwable);
                return "调用出错";
        };
    }
}
