package cc.mrbird.febs.gateway.filter;

import cc.mrbird.febs.common.entity.FebsConstant;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description 自定义过滤器
 *              1.因为PreDecorationFilter过滤器处理上下文,且其order为5,
 *              所以只要自定义过滤器order大于5,就可以获取到上下文
 * @Author 54045
 * @Date 2020-06-21 13:28
 */
@Slf4j
@Component
public class FebsGatewayRequestFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 6;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        String serviceId = (String) ctx.get(FilterConstants.SERVICE_ID_KEY);
        HttpServletRequest request = ctx.getRequest();
        String host = request.getRemoteHost();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        log.info("请求URI:{},HTTP Method:{},请求IP:{},ServerId:{}",uri,method,host,serviceId);

        byte[] token = Base64Utils.encode(FebsConstant.ZUUL_TOKEN_VALUE.getBytes());
        ctx.addZuulRequestHeader(FebsConstant.ZUUL_TOKEN_HEADER,new String(token));
        return null;
    }
}
