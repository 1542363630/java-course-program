package com.example.courseprogram.controller;

import com.example.courseprogram.model.DO.Fee;
import com.example.courseprogram.model.DO.HomeworkInfo;
import com.example.courseprogram.model.DTO.DataRequest;
import com.example.courseprogram.model.DTO.DataResponse;
import com.example.courseprogram.service.FeeService;
import com.example.courseprogram.service.HomeworkInfoService;
import com.example.courseprogram.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/homeworkInfo")
public class HomeworkInfoController {
    @Autowired
    HomeworkInfoService homeworkInfoService;

    //增加或者修改数据
    @PostMapping("/add")
    public DataResponse addAndUpdHomeworkInfo(@RequestBody DataRequest dataRequest){
        return homeworkInfoService.addOrUpdHomeworkInfo(JsonUtil.parse(dataRequest.get("homeworkInfo"), HomeworkInfo.class));
    }

    //上传文件
    @PostMapping("/upload")
    public DataResponse upload(@RequestBody byte[] file,@RequestParam("fileName") String fileName){
        return homeworkInfoService.upload(file,fileName);
    }

    //下载文件
    @PostMapping("/download")
    public ResponseEntity<byte[]> download(@RequestBody DataRequest dataRequest){
        return homeworkInfoService.download(JsonUtil.parse(dataRequest.get("url"), String.class));
    }

    //根据id删除
    @PostMapping("/deleteById")
    public DataResponse deleteById(@RequestBody DataRequest dataRequest){
        return homeworkInfoService.deleteById(JsonUtil.parse(dataRequest.get("id"),Integer.class));
    }

    //根据课程id查找
    @PostMapping("/findByCourseId")
    public DataResponse findByCourseId(@RequestBody DataRequest dataRequest){
        return homeworkInfoService.findByCourseId(JsonUtil.parse(dataRequest.get("id"),Integer.class));
    }

    //获取所有数据
    @PostMapping("/findAll")
    public DataResponse findAll(){
        return homeworkInfoService.findAll();
    }

}

