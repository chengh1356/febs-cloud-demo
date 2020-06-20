package cc.mrbird.febs.common.interceptor;

import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.utils.FebsUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.util.Base64Utils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;

/**
 * @Description 全局拦截器
 *              1.校验Zuul Token
 * @Author 54045
 * @Date 2020-06-21 13:44
 */
public class FebsServerProtectInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //从请求头中获取Zuul Token
        String token = request.getHeader(FebsConstant.ZUUL_TOKEN_HEADER);
        String zuulToken = new String(Base64Utils.encode(FebsConstant.ZUUL_TOKEN_VALUE.getBytes()));
        // 校验Zuul Token的正确性
        if(StringUtils.equals(zuulToken,token)){
            return true;
        }else{
            FebsResponse febsResponse = new FebsResponse();
            FebsUtil.makeResponse(response,MediaType.APPLICATION_JSON_UTF8_VALUE,HttpServletResponse.SC_FORBIDDEN,febsResponse.message("请通过网关获取资源"));
            return false;
        }

    }
}
