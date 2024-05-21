package com.example.courseprogram.service;

import com.example.courseprogram.model.DTO.DataResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.DataTruncation;
import java.time.LocalDate;

@Service
public class FileService {

    @Value("${web.upload-path}")
    String path;

    public DataResponse upload(byte[] file, String fileName){
        if(file==null||fileName==null)return DataResponse.failure(401,"信息不完整");
        if (!(file.length==0)) {
            if (file.length > 209715200) { // 200MB
                return DataResponse.failure(402,"文件过大(最大200MB)");
            }
            try {
                String path_mid=fileName.substring(0,fileName.lastIndexOf("\\"));
                fileName=fileName.substring(fileName.lastIndexOf("\\")+1);
                String directoryPath=path+ path_mid+"\\"+ LocalDate.now();
                File directory = new File(directoryPath);
                if (!directory.exists()) {
                    // 目录不存在，创建目录及其所有必需的父目录
                    boolean isDirectoryCreated = directory.mkdirs();
                    if (!isDirectoryCreated) {
                        System.out.println("目录创建失败: " + directoryPath);
                    }
                }
                Path dirPath = Paths.get(directoryPath);
                // 文件处理逻辑，例如保存到服务器上的某个目录
                // 这里可以使用文件输出流来保存文件
                FileOutputStream fos = new FileOutputStream(dirPath +"\\"+ fileName);
                fos.write(file);
                fos.close();
                File nowFile = new File(dirPath +"\\"+ fileName);

                return DataResponse.okM("success:"+dirPath +"\\"+fileName);
            } catch (Exception e) {
                return DataResponse.failure(402,"File upload failed: " + e.getMessage());
            }
        } else {
            return DataResponse.failure(401,"请上传一个文件!");
        }

    }

    public ResponseEntity<byte[]> download(String url){
        try {
            Path filePath = Paths.get(url);
            Resource resource = new UrlResource(filePath.toUri());
            byte[] fileContent= Files.readAllBytes(filePath);
            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok(fileContent);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    public DataResponse delete(String url){
        if(url==null)return DataResponse.failure(401,"信息不完整");
        Path filePath = Paths.get(url);
        if(!Files.exists(filePath)){
            return DataResponse.failure(403,"该文件不存在（路径错误或已删除）");
        }
        try {
            Files.delete(filePath);
            if(Files.exists(filePath)){
                return DataResponse.failure(403,"删除失败");
            }
            else {
                return DataResponse.okM("删除成功");
            }
        }catch (Exception e){
            return DataResponse.failure(402,e.getMessage());
        }
    }

}
