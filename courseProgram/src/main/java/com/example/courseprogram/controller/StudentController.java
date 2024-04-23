package com.example.courseprogram.controller;

import com.example.courseprogram.model.DO.Student;
import com.example.courseprogram.model.DO.User;
import com.example.courseprogram.model.DTO.DataRequest;
import com.example.courseprogram.model.DTO.DataResponse;
import com.example.courseprogram.model.DTO.StudentInfo;
import com.example.courseprogram.service.StudentService;
import com.example.courseprogram.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    //添加学生，初始账号和密码为学号
    @PostMapping("/addStudent")
    public DataResponse addStudent(@RequestBody DataRequest dataRequest){
        return studentService.addStudent(JsonUtil.parse(dataRequest.get("studentInfo"), StudentInfo.class),JsonUtil.parse(dataRequest.get("user"), User.class));
    }

    //删除学生
    @PostMapping("/deleteStudent")
    public DataResponse deleteStudent(@RequestBody DataRequest dataRequest){
        return studentService.deleteStudent(JsonUtil.parse(dataRequest.get("student"), Student.class));
    }

    //修改学生信息
    @PostMapping("/addOrUpdateStudent")
    public DataResponse addOrUpdateStudent(@RequestBody DataRequest dataRequest){
        return studentService.addOrUpdateStudent(JsonUtil.parse(dataRequest.get("student"), Student.class));
    }

    //根据userId查找学生
    @PostMapping("/findByUserId")
    public DataResponse findByUserId(@RequestBody DataRequest dataRequest){
        return studentService.findByUserId(JsonUtil.parse(dataRequest.get("user"), User.class));
    }

    //获取所有学生
    @PostMapping("/getStudentList")
    public DataResponse getStudentList(){
        return studentService.getStudentList();
    }
}
