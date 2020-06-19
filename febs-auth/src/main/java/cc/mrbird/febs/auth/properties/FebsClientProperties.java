package cc.mrbird.febs.auth.properties;

import lombok.Data;

/**
 * @Description
 * @Author cgh
 * @Date 2020-06-19 下午 3:09
 */
@Data
public class FebsClientProperties {
    private String client;
    private String secret;
    private String grantType = "password,authorization_code,refresh_token";
    private String scope = "all";
}
