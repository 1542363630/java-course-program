package com.example.courseprogram.utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;

import javax.swing.*;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

public class JsonUtil {
    public static <T> T parse(Object object, Class<T> tClass){
        LinkedHashMap<String,Object> o = (LinkedHashMap<String, Object>) object;
        T t = JSONObject.parseObject(JSON.toJSONString(o), tClass);
//        Set<String> set=o.keySet();
//        for(String s:set){
//            if(o.get(s).getClass()==o.getClass()){
//
//            }
//        }
        Field[] fields = tClass.getDeclaredFields();
        for(Field field:fields){
            field.setAccessible(true);
            if(o.containsKey(field.getName())){
                if (o.get(field.getName()).getClass()==o.getClass()){
                    try {
                        field.set(t,parse(o.get(field.getName()),field.getType()));
                    }catch (Exception e){

                    }
                    System.out.println("ok");
                }
            }
        }
        return t;
    }
}
