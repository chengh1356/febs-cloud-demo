package cc.mrbird.febs.common.handler;

import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.utils.FebsUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description 认证无效异常处理
 *              token令牌无效，权限验证不通过，401
 * @Author cgh
 * @Date 2020-06-19 下午 4:56
 */

public class FebsAuthExceptionEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        FebsResponse febsResponse = new FebsResponse();
        //MediaType.APPLICATION_JSON_UTF8_VALUE相当于"application/json;charset=UTF-8",
        //HttpServletResponse.SC_UNAUTHORIZED相当于401
        FebsUtil.makeResponse(httpServletResponse,MediaType.APPLICATION_JSON_UTF8_VALUE,HttpServletResponse.SC_UNAUTHORIZED,febsResponse.message("token无效"));
    }
}

























