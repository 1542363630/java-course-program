package com.example.courseprogram.controller;

import com.example.courseprogram.model.DO.Teacher;
import com.example.courseprogram.model.DO.User;
import com.example.courseprogram.model.DTO.DataRequest;
import com.example.courseprogram.model.DTO.DataResponse;
import com.example.courseprogram.service.TeacherService;
import com.example.courseprogram.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @PostMapping("/addTeacher")
    public DataResponse addTeacher(@RequestBody DataRequest dataRequest){
        return teacherService.addTeacher(JsonUtil.prase(dataRequest.get("teacher"), Teacher.class));
    }

    @PostMapping("/deleteTeacher")
    public DataResponse deleteTeacher(@RequestBody DataRequest dataRequest){
        return teacherService.deleteTeacher(JsonUtil.prase(dataRequest.get("teacher"), Teacher.class));
    }

    @PostMapping("/addOrUpdateTeacher")
    public DataResponse addOrUpdateTeacher(@RequestBody DataRequest dataRequest){
        return teacherService.addOrUpdateTeacher(JsonUtil.prase(dataRequest.get("teacher"), Teacher.class));
    }

    @PostMapping("/findByUserId")
    public DataResponse findByUserId(@RequestBody DataRequest dataRequest){
        return teacherService.findByUserId(JsonUtil.prase(dataRequest.get("user"), User.class));
    }

    @PostMapping("/getTeacherList")
    public DataResponse getTeacherList(){
        return teacherService.getTeacherList();
    }

}
