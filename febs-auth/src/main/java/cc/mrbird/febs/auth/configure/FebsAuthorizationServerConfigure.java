package cc.mrbird.febs.auth.configure;

import cc.mrbird.febs.auth.properties.FebsAuthProperties;
import cc.mrbird.febs.auth.properties.FebsClientProperties;
import cc.mrbird.febs.auth.service.FebsUserDetailService;
import cc.mrbird.febs.auth.translator.FebsWebResponseExceptionTranslator;
import com.sun.deploy.services.DefaultService;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @Description 和认证服务器相关的安全配置类
 * @Author 54045
 * @Date 2020-06-16 22:19
 */
@Configuration
@EnableAuthorizationServer
public class FebsAuthorizationServerConfigure extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private FebsUserDetailService userDetailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private FebsAuthProperties authProperties;

    @Autowired
    private FebsWebResponseExceptionTranslator exceptionTranslator;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        FebsClientProperties[] clientsArray = authProperties.getClients();
        InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
        if(ArrayUtils.isNotEmpty(clientsArray)){
            for (FebsClientProperties client:clientsArray){
                if(StringUtils.isBlank(client.getClient())){
                    throw new Exception("client不能为空");
                }
                if(StringUtils.isBlank(client.getSecret())){
                    throw new Exception("secret不能为空");
                }
                String[] grantTypes = StringUtils.splitByWholeSeparatorPreserveAllTokens(client.getGrantType(),",");
                builder.withClient(client.getClient())
                        .secret(passwordEncoder.encode(client.getSecret()))
                        .authorizedGrantTypes(grantTypes)
                        .scopes(client.getScope());
            }
        }
        clients.inMemory()
                //1.客户端从认证服务器获取令牌的时候，必须使用client_id为febs，client_secret为123456的标识来获取
                .withClient("febs")
                .secret(passwordEncoder.encode("123456"))
                //2.获取令牌的模式为password，并且可以通过refresh_token来获取新的令牌
                .authorizedGrantTypes("password","refresh_token")
                //3.在获取client_id为febs的令牌的时候，scope只能指定为all，否则将获取失败
                .scopes("all");
    }
    //客户端与服务器配置末端操作
    @Override
    @SuppressWarnings("all")
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore())
                .userDetailsService(userDetailService)
                .authenticationManager(authenticationManager)
                .tokenServices(defaultTokenServices())
                .exceptionTranslator(exceptionTranslator);
    }

    //指定令牌的基本配置
    @Primary
    @Bean
    public AuthorizationServerTokenServices defaultTokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore());
        //刷新token
        tokenServices.setSupportRefreshToken(true);
        //24小时内有效
        tokenServices.setAccessTokenValiditySeconds(authProperties.getAccessTokenValiditySeconds());
        //7天内刷新的有效时间
        tokenServices.setRefreshTokenValiditySeconds(authProperties.getRefreshTokenValiditySeconds());
        return tokenServices;
    }

    //令牌存储到redis中
    @Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

}
