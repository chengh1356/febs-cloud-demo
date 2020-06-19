package cc.mrbird.febs.server.test.handler;

import cc.mrbird.febs.common.handler.BaseExceptionHandler;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Description 微服务独有的异常,都用GlobalExceptionHandler
 * @Author 54045
 * @Date 2020-06-20 0:26
 */
@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)  //责任链排到最后
public class GlobalExceptionHandler extends BaseExceptionHandler {
}
