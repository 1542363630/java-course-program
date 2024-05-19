package com.example.courseprogram.controller;

import com.alibaba.fastjson2.JSON;
import com.example.courseprogram.model.DO.DailyActivityStudent;
import com.example.courseprogram.model.DO.InnovativePracticeStudent;
import com.example.courseprogram.model.DO.Student;
import com.example.courseprogram.model.DTO.DataRequest;
import com.example.courseprogram.model.DTO.DataResponse;
import com.example.courseprogram.service.DailyActivityStudentService;
import com.example.courseprogram.service.InnovativePracticeStudentService;
import com.example.courseprogram.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/innovativePracticeStudent")
public class InnovativePracticeStudentController {
    @Autowired
    InnovativePracticeStudentService innovativePracticeStudentService;

    //增加或者修改数据
    @PostMapping("/add")
    public DataResponse addAndUpdInnovativePracticeStudent(@RequestBody DataRequest dataRequest){
        return innovativePracticeStudentService.addAndUpdInnovativePracticeStudent(JsonUtil.parse(dataRequest.get("innovativePracticeStudent"), InnovativePracticeStudent.class));
    }

    //根据id删除
    @PostMapping("/deleteById")
    public DataResponse deleteById(@RequestBody DataRequest dataRequest){
        return innovativePracticeStudentService.deleteById(JsonUtil.parse(dataRequest.get("id"),Integer.class));
    }

    //查找某学生的信息
    @PostMapping("/findByStudent")
    public DataResponse findByStudentId(@RequestBody DataRequest dataRequest){
        return innovativePracticeStudentService.findByStudentId(JsonUtil.parse(dataRequest.get("id"), Long.class));
    }

    //根据id和学生列表删除
    @PostMapping("/deleteByIdAndStudents")
    public DataResponse deleteByIdAndStudents(@RequestBody DataRequest dataRequest){
        return innovativePracticeStudentService.deleteByIdAndStudents(JsonUtil.parse(dataRequest.get("id"), Integer.class), JSON.parseArray(JSON.toJSONString(dataRequest.get("students")), Student.class));
    }

    //删除某学生的所有信息
    @PostMapping("/deleteByStudent")
    public DataResponse deleteByStudentId(@RequestBody DataRequest dataRequest){
        return innovativePracticeStudentService.deleteByStudentId(JsonUtil.parse(dataRequest.get("id"), Long.class));
    }

    //查找某活动的信息
    @PostMapping("/findByInnovativePractice")
    public DataResponse findByInnovativePracticeId(@RequestBody DataRequest dataRequest){
        return innovativePracticeStudentService.findByInnovativePracticeId(JsonUtil.parse(dataRequest.get("id"), Integer.class));
    }

    //删除某活动的所有信息
    @PostMapping("/deleteByInnovativePractice")
    public DataResponse deleteByInnovativePracticeId(@RequestBody DataRequest dataRequest){
        return innovativePracticeStudentService.deleteByInnovativePracticeId(JsonUtil.parse(dataRequest.get("id"), Integer.class));
    }

    //获取所有数据
    @PostMapping("/findAll")
    public DataResponse findAll(){
        return innovativePracticeStudentService.findAll();
    }
}
