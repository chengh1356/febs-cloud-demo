package cc.mrbird.febs.common.configure;

import cc.mrbird.febs.common.entity.FebsConstant;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.util.Base64Utils;

/**
 * @Description
 * @Author 54045
 * @Date 2020-06-20 9:21
 */
public class FebsOAuth2FeignConfigure {

    /**
     * 给feign服务间调用时,添加token令牌
     * @return
     */
    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor(){
        return requestTemplate -> {
            // 添加 Zuul Token
            String zuulToken = new String(Base64Utils.encode(FebsConstant.ZUUL_TOKEN_VALUE.getBytes()));
            requestTemplate.header(FebsConstant.ZUUL_TOKEN_HEADER,zuulToken);

            Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
            if(details instanceof OAuth2AuthenticationDetails){
                String tokenValue = ((OAuth2AuthenticationDetails) details).getTokenValue();
                requestTemplate.header(HttpHeaders.AUTHORIZATION,"bearer "+ tokenValue);
            }
        };
    }
}
