package com.example.courseprogram.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class FileUtil {

    //获得新文件名
    public static String getFileName(String fileOriginName){
        return getUUID()+ getSuffix(fileOriginName);
    }

    //获取文件后缀
    public static String getSuffix(String fileName){
        return fileName.substring(fileName.lastIndexOf("."));
    }

    //UUID来生成随机的文件名
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }

    //文件上传
    public static String upload(MultipartFile file,String path,String fileName){
        //生成新文件名
        String newFileName = getFileName(fileName);
        String realPath = path + newFileName;

        File dest = new File(realPath);

        //判断文件父目录是否存在
        if(!dest.getParentFile().exists()){
            boolean success = dest.getParentFile().mkdir();
            if (!success) return "生成父目录失败";
        }

        try {
            //保存文件
            file.transferTo(dest);
            return newFileName;
        } catch (IllegalStateException | IOException e) {
            return "报错了";
        }
    }
}
