package cc.mrbird.febs.common.annotation;

import cc.mrbird.febs.common.configure.FebsLettuceRedisConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Description 自定义redis的序列化
 * @Author 54045
 * @Date 2020-06-21 17:40
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(FebsLettuceRedisConfigure.class)
public @interface EnableFebsLettuceRedis {
}
