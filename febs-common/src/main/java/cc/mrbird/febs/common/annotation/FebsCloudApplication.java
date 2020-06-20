package cc.mrbird.febs.common.annotation;

import cc.mrbird.febs.common.selector.FebsCloudApplicationSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Description 统一配置
 *              1.@EnableFebsServerProtect，开启微服务防护，避免客户端绕过网关直接请求微服务；
 *              2.@EnableFebsOauth2FeignClient，开启带令牌的Feign请求，避免微服务内部调用出现401异常；
 *              3.@EnableFebsAuthExceptionHandler，认证类型异常翻译。
 * @Author 54045
 * @Date 2020-06-21 14:21
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(value = FebsCloudApplicationSelector.class)
public @interface FebsCloudApplication {
}
