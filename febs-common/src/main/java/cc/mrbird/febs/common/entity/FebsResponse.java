package cc.mrbird.febs.common.entity;

import lombok.Data;

import java.io.OutputStream;
import java.util.HashMap;

/**
 * @Description 通用的响应对象
 * @Author 54045
 * @Date 2020-06-17 20:50
 */
@Data
public class FebsResponse extends HashMap<String,Object> {

    public FebsResponse message(String message){
        this.put("message",message);
        return this;
    }

    public FebsResponse data(Object data){
        this.put("data",data);
        return this;
    }

    @Override
    public FebsResponse put(String key, Object value){
        super.put(key,value);
        return this;
    }

    public String getMessage(){
        return String.valueOf(get("message"));
    }

    public Object getData(){
        return get("data");
    }
}
