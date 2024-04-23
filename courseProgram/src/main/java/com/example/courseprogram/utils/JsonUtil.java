package com.example.courseprogram.utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;

public class JsonUtil {
    public static <T> T parse(Object o, Class<T> tClass){
        return JSONObject.parseObject(JSON.toJSONString(o), tClass);
    }
}
