package cc.mrbird.febs.gateway.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @Description 跨域问题解决
 * @Author 54045
 * @Date 2020-06-21 14:44
 */
@Configuration
public class FebsGateWayCorsConfigure {

    @Bean
    public CorsFilter corsFilter(){
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);    //允许Cookie跨域
        corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);  //请求头部允许携带任何内容
        corsConfiguration.addAllowedOrigin(CorsConfiguration.ALL);  //允许任何来源
        corsConfiguration.addAllowedMethod(CorsConfiguration.ALL);  //允许任何HTTP方法
        source.registerCorsConfiguration("/**",corsConfiguration);
        return new CorsFilter(source);
    }
}
