package com.example.courseprogram.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtil {

    public static String getTime(){
        Date date=new Date();
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }
}
