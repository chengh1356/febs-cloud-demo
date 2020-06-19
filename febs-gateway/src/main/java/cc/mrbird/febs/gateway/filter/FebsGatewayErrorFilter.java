package cc.mrbird.febs.gateway.filter;

import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.utils.FebsUtil;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;


import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description 自定义Zuul异常处理
 *              1.请求超时  504
 *              2.微服务模块不可用 500
 * @Author 54045
 * @Date 2020-06-19 23:53
 */
@Slf4j
@Component
public class FebsGatewayErrorFilter extends SendErrorFilter {

    @Override
    public Object run() {
        try {
            FebsResponse febsResponse = new FebsResponse();
            RequestContext ctx = RequestContext.getCurrentContext();
            //服务ID
            String serviceId = (String) ctx.get(FilterConstants.SERVICE_ID_KEY);

            ExceptionHolder exception = this.findZuulException(ctx.getThrowable());
            String errorCause = exception.getErrorCause();
            Throwable throwable = exception.getThrowable();
            //异常信息
            String message = throwable.getMessage();
            //若为空,则取错误原因
            message = StringUtils.isBlank(message) ? errorCause : message;
            //处理异常信息
            febsResponse = resolveExceptionMessage(message,serviceId,febsResponse);

            HttpServletResponse response = ctx.getResponse();
            FebsUtil.makeResponse(
                    response, MediaType.APPLICATION_JSON_UTF8_VALUE,
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR,febsResponse
            );
            log.error("Zuul sendError: {}" , febsResponse.getMessage());
        } catch (Exception ex) {
            log.error("Zuul sendError",ex);
            ReflectionUtils.rethrowRuntimeException(ex);
        }
        return null;
    }

    private FebsResponse resolveExceptionMessage(String message, String serviceId, FebsResponse febsResponse) {
        if(StringUtils.containsIgnoreCase(message,"time out")){
            return febsResponse.message("请求" + serviceId + "服务超时");
        }

        if(StringUtils.containsIgnoreCase(message,"forwarding error")){
            return febsResponse.message(serviceId + "服务不可用");
        }
        return febsResponse.message("Zuul请求" + serviceId + "服务异常");
    }
}
