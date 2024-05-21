package com.example.courseprogram.controller;

import com.example.courseprogram.model.DTO.DataRequest;
import com.example.courseprogram.model.DTO.DataResponse;
import com.example.courseprogram.service.FileService;
import com.example.courseprogram.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/file")
public class FileController {
    @Autowired
    FileService fileService;

    //上传文件
    @PostMapping("/upload")
    public DataResponse upload(@RequestBody byte[] file, @RequestParam("fileName") String fileName){
        return fileService.upload(file,fileName);
    }

    //下载文件
    @PostMapping("/download")
    public ResponseEntity<byte[]> download(@RequestBody DataRequest dataRequest){
        return fileService.download(JsonUtil.parse(dataRequest.get("url"), String.class));
    }

    //删除文件
    @PostMapping("/delete")
    public DataResponse delete(@RequestBody DataRequest dataRequest){
        return fileService.delete(JsonUtil.parse(dataRequest.get("url"), String.class));
    }
}
