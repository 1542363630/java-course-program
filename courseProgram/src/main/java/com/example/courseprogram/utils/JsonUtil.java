package com.example.courseprogram.utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.example.courseprogram.model.DO.User;

public class JsonUtil {
    static public <T> T prase(Object o,Class<T> tClass){
        return JSONObject.parseObject(JSON.toJSONString(o), tClass);
    }
}
