package cc.mrbird.febs.common.annotation;

import cc.mrbird.febs.common.configure.FebsOAuth2FeignConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Description feign服务间调用配置
 *              1.服务间调用加token令牌
 *              2.开启带令牌的Feign请求，避免微服务内部调用出现401异常
 * @Author 54045
 * @Date 2020-06-20 9:32
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(value = FebsOAuth2FeignConfigure.class)
public @interface EnableFebsOauth2FeignClient {

}
