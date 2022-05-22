package com.doromv.utils;

import lombok.Data;
import java.util.HashMap;
import java.util.Map;

@Data
public class ResponseResult<T> {

    private Integer code; //编码：1成功，0和其它数字为失败

    private String msg; //错误信息

    private T data; //数据

    private Map map = new HashMap(); //动态数据

    public static <T> ResponseResult<T> success(T object) {
        ResponseResult<T> responseResult = new ResponseResult<T>();
        responseResult.data = object;
        responseResult.code = 1;
        return responseResult;
    }

    public static <T> ResponseResult<T> error(String msg) {
        ResponseResult responseResult = new ResponseResult();
        responseResult.msg = msg;
        responseResult.code = 0;
        return responseResult;
    }

    public ResponseResult<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }

}
