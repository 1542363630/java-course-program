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

    //添加教师 初始账号和密码为工号
    @PostMapping("/addTeacher")
    public DataResponse addTeacher(@RequestBody DataRequest dataRequest){
        return teacherService.addTeacher(JsonUtil.parse(dataRequest.get("teacher"), Teacher.class));
    }

    //删除教师
    @PostMapping("/deleteTeacher")
    public DataResponse deleteTeacher(@RequestBody DataRequest dataRequest){
        return teacherService.deleteTeacher(JsonUtil.parse(dataRequest.get("teacher"), Teacher.class));
    }

    //修改教师信息
    @PostMapping("/addOrUpdateTeacher")
    public DataResponse addOrUpdateTeacher(@RequestBody DataRequest dataRequest){
        return teacherService.addOrUpdateTeacher(JsonUtil.parse(dataRequest.get("teacher"), Teacher.class));
    }

    //根据userId查找教师
    @PostMapping("/findByUserId")
    public DataResponse findByUserId(@RequestBody DataRequest dataRequest){
        return teacherService.findByUserId(JsonUtil.parse(dataRequest.get("user"), User.class));
    }

    //获取所有教师
    @PostMapping("/getTeacherList")
    public DataResponse getTeacherList(){
        return teacherService.getTeacherList();
    }

}
