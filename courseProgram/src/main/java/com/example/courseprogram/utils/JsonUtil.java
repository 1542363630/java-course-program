package com.example.courseprogram.utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
public class JsonUtil {
    public static <T> T parse(Object object, Class<T> tClass){
        LinkedHashMap<?,?> o=new LinkedHashMap<>();
        T t;
        if(object instanceof LinkedHashMap<?,?>){
            o = (LinkedHashMap<?, ?>) object;
            t = JSONObject.parseObject(JSON.toJSONString(o), tClass);
        }
        else{
            t=JSON.parseObject(JSON.toJSONString(object),tClass);
            return t;
        }
        Field[] fields = tClass.getDeclaredFields();
        for(Field field:fields){
            field.setAccessible(true);
            if(o.containsKey(field.getName())){
                if (o.get(field.getName()).getClass()==o.getClass()){
                    try {
                        field.set(t,parse(o.get(field.getName()),field.getType()));
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
        return t;
    }
}
