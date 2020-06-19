package cc.mrbird.febs.server.test.service;

import cc.mrbird.febs.common.entity.FebsServerConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description
 * @Author 54045
 * @Date 2020-06-20 8:50
 */
@FeignClient(value = FebsServerConstant.FEBS_SERVER_SYSTEM,contextId = "helloServiceClient",fallbackFactory = HelloServiceFallback.class)
public interface IHelloService {

    @GetMapping("hello")
    String hello (@RequestParam("name")String name);
}
