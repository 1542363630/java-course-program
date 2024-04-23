package com.example.courseprogram.utils;

import com.example.courseprogram.model.DO.InnovativePractice;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtil {

    public static String getTime(){
        Date date=new Date();
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

    public static <T> boolean checkInfo(T t){
        try {
            boolean flag = true;
            if(t==null){
                flag=false;
            }
            else{
                Field[] fields= t.getClass().getDeclaredFields();
                for(Field field:fields){
                    field.setAccessible(true);
                    if(field.get(t)==null && !field.getName().contains("id")){
                        if(field.getType()== String.class)field.set(t,"");
                        else if(field.getType()== Integer.class)field.set(t,0);
                        flag=false;
                    }
                    field.setAccessible(false);
                }
            }
            return flag;
        }
        catch (IllegalAccessException e){
            System.out.println("检查字段是否为空时出现异常");
            return false;
        }
    }
}
