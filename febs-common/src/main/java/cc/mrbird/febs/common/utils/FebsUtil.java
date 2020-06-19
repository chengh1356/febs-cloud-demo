package cc.mrbird.febs.common.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description
 * @Author cgh
 * @Date 2020-06-19 下午 5:07
 */
@Slf4j
public class FebsUtil {
    public static void makeResponse(HttpServletResponse response,String contentType,
                                    int status,Object value)throws IOException{
        response.setContentType(contentType);
        response.setStatus(status);
        response.getOutputStream().write(JSONObject.toJSONString(value).getBytes());
    }
}

































