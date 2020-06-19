package cc.mrbird.febs.common.annotation;

import cc.mrbird.febs.common.configure.FebsAuthExceptionConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Description  认证配置注解
 *              1.token无效
 *              2.没有权限访问该资源
 *              注解用以驱动配置类,注入Spring IOC 容器中
 *              注解(EnableFebsAuthExceptionHandler)
 *              --> 配置类(FebsAuthExceptionConfigure.class)
 *              -> 需要注入的类 (FebsAccessDeniedHandler.class,FebsAuthExceptionEntryPoint.class)
 * @Author cgh
 * @Date 2020-06-19 下午 5:33
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
//配置类引入
@Import(FebsAuthExceptionConfigure.class)
public @interface EnableFebsAuthExceptionHandler {
}
