package cc.mrbird.febs.common.handler;

import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.exception.FebsAuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Description 全局异常处理器
 * @Author 54045
 * @Date 2020-06-20 0:17
 */
@Slf4j
public class BaseExceptionHandler {
    //异常 500
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public FebsResponse handleException(Exception ex){
        log.error("系统内部异常,异常信息",ex);
        return new FebsResponse().message("系统内部异常");
    }
    @ExceptionHandler(value = FebsAuthException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public FebsResponse handleFebsAuthException(FebsAuthException ex){
        log.error("系统错误",ex);
        return new FebsResponse().message(ex.getMessage());
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public FebsResponse handleAccessDeniedException(){
        return new FebsResponse().message("没有权限访问该资源");
    }
}
