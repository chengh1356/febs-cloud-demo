package cc.mrbird.febs.auth.configure;

import cc.mrbird.febs.auth.filter.ValidateCodeFilter;
import cc.mrbird.febs.auth.service.FebsUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * @Description 安全配置
 *              用于处理和令牌相关的请求
 * @Author 54045
 * @Date 2020-06-14 14:34
 */
@Order(2)   //因为http的责任链order为3,所以要优先于http,率先验证
@EnableWebSecurity
public class FebsSecurityConfigure extends WebSecurityConfigurerAdapter {

    @Autowired
    private FebsUserDetailService userDetailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ValidateCodeFilter validateCodeFilter;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean()throws Exception{
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http)throws Exception{
        //在用户名和密码过滤器前,添加验证码过滤
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .requestMatchers()
                //只匹配oauth开头的请求
                .antMatchers("/oauth/**")
            .and()
                .authorizeRequests()
                .antMatchers("/oauth/**").authenticated()
            .and()
                .csrf().disable();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth)throws Exception{
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder);
    }
































}
