package cc.mrbird.febs.server.system.configure;

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
 * @Author cgh
 * @Date 2020-06-18 下午 4:49
 */
@Configuration
@EnableResourceServer
public class FebsServerSystemResourceServerConfigure extends ResourceServerConfigurerAdapter {

    @Autowired
    private FebsAccessDeniedHandler febsAccessDeniedHandler;

    @Autowired
    private FebsAuthExceptionEntryPoint febsAuthExceptionEntryPoint;
    /**
     * 所有访问febs-server-system的请求都需要认证,
     * 只有通过认证服务器发放的令牌才能进行访问
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception{
        http.csrf().disable()
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





























