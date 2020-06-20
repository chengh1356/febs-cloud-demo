package cc.mrbird.febs.common.annotation;

import cc.mrbird.febs.common.configure.FebsServerProtectConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Description 校验zuul token
 *              1.开启微服务防护，避免客户端绕过网关直接请求微服务
 * @Author 54045
 * @Date 2020-06-21 13:59
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(FebsServerProtectConfigure.class)
public @interface EnableFebsServerProtect {
}
