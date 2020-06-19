package cc.mrbird.febs.common.configure;

import cc.mrbird.febs.common.handler.FebsAccessDeniedHandler;
import cc.mrbird.febs.common.handler.FebsAuthExceptionEntryPoint;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * @Description 把异常配置类注入到IOC容器中
 * @Author cgh
 * @Date 2020-06-19 下午 5:26
 */

public class FebsAuthExceptionConfigure {

    @Bean
    //当IOC容器中没有指定名称或类型的Bean的时候，就注册它
    @ConditionalOnMissingBean(name="accessDeniedHandler")
    public FebsAccessDeniedHandler accessDeniedHandler(){
        return new FebsAccessDeniedHandler();
    }

    @Bean
    @ConditionalOnMissingBean(name = "authExceptionEntryPoint")
    public FebsAuthExceptionEntryPoint authExceptionEntryPoint(){
        return new FebsAuthExceptionEntryPoint();
    }
}

































