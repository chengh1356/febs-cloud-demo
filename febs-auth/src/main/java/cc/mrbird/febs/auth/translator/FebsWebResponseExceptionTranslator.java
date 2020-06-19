package cc.mrbird.febs.auth.translator;

import cc.mrbird.febs.common.entity.FebsResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.UnsupportedGrantTypeException;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.stereotype.Component;

/**
 * @Description 异常翻译器
 * @Author cgh
 * @Date 2020-06-19 下午 4:39
 */
@Slf4j
@Component
public class FebsWebResponseExceptionTranslator implements WebResponseExceptionTranslator {
    @Override
    public ResponseEntity translate(Exception e) throws Exception {
        //返回状态码500
        ResponseEntity.BodyBuilder status = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        FebsResponse response = new FebsResponse();
        String message = "认证失败";
        log.error(message,e);
        if(e instanceof UnsupportedGrantTypeException){
            message = "不支持该认证类型";
            return status.body(response.message(message));
        }
        if(e instanceof InvalidGrantException){
            if(StringUtils.containsIgnoreCase(e.getMessage(),"Invalid refresh token")){
                message = "refresh token 无效";
                return status.body(response.message(message));
            }
            if(StringUtils.containsIgnoreCase(e.getMessage(),"locked")){
                message = "用户已被锁定，请联系管理员";
                return status.body(response.message(message));
            }
            message = "用户名或密码错误";
            return status.body(response.message(message));
        }
        return status.body(response.message(message));
    }
}












































