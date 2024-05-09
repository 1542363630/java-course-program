package com.example.courseprogram.controller;

import com.example.courseprogram.model.DO.Homework;
import com.example.courseprogram.model.DTO.DataRequest;
import com.example.courseprogram.model.DTO.DataResponse;
import com.example.courseprogram.service.HomeworkService;
import com.example.courseprogram.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/homework")
public class HomeworkController {
    @Autowired
    HomeworkService homeworkService;

    //增加或者修改数据
    @PostMapping("/add")
    public DataResponse addAndUpdHomework(@RequestBody DataRequest dataRequest){
        return homeworkService.addAndUpdHomework(JsonUtil.parse(dataRequest.get("homework"), Homework.class));
    }

    //根据id删除
    @PostMapping("/deleteById")
    public DataResponse deleteById(@RequestBody DataRequest dataRequest){
        return homeworkService.deleteById(JsonUtil.parse(dataRequest.get("id"),Integer.class));
    }

    //删除某学生的所有信息
    @PostMapping("/deleteByStudent")
    public DataResponse deleteByStudentId(@RequestBody DataRequest dataRequest){
        return homeworkService.deleteByStudentId(JsonUtil.parse(dataRequest.get("id"), Long.class));
    }

    //查找某学生的信息
    @PostMapping("/findByStudent")
    public DataResponse findByStudentId(@RequestBody DataRequest dataRequest){
        return homeworkService.findByStudentId(JsonUtil.parse(dataRequest.get("id"), Long.class));
    }

    //根据作业信息id找作业
    @PostMapping("/findByHomeworkInfoId")
    public DataResponse findByHomeworkInfoId(@RequestBody DataRequest dataRequest){
        return homeworkService.findByHomeworkInfoId(JsonUtil.parse(dataRequest.get("id"), Integer.class));
    }

    //获取所有数据
    @PostMapping("/findAll")
    public DataResponse findAll(){
        return homeworkService.findAll();
    }

}
