package cc.mrbird.febs.auth.configure;

import cc.mrbird.febs.common.handler.FebsAccessDeniedHandler;
import cc.mrbird.febs.common.handler.FebsAuthExceptionEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @Description 资源服务器配置
 *              客户端只能通过OAuth2协议发放的令牌来从资源服务器中获取受保护的资源。
 * @Author 54045
 * @Date 2020-06-16 22:01
 */
@Configuration
@EnableResourceServer   //开启资源服务器相关配置
public class FebsResourceServerConfigure extends ResourceServerConfigurerAdapter {

    @Autowired
    private FebsAccessDeniedHandler febsAccessDeniedHandler;

    @Autowired
    private FebsAuthExceptionEntryPoint febsAuthExceptionEntryPoint;

    @Override
    public void configure(HttpSecurity http) throws Exception{
        //用于资源的保护,其他资源请求失效
        http.csrf().disable()
                //对所有请求都生效
                .requestMatchers().antMatchers("/**")
                .and()
                .authorizeRequests()
                .antMatchers("/**").authenticated();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.accessDeniedHandler(febsAccessDeniedHandler)
                .authenticationEntryPoint(febsAuthExceptionEntryPoint);
    }
}
