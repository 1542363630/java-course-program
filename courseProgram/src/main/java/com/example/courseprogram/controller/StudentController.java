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
        return studentService.addStudent(dataRequest);
    }

    @PostMapping("/deleteStudent")
    public DataResponse deleteStudent(@RequestBody DataRequest dataRequest){
        return studentService.deleteStudent(JsonUtil.prase(dataRequest.get("student"), Student.class));
    }

    @PostMapping("/addOrUpdateStudent")
    public DataResponse addOrUpdateStudent(@RequestBody DataRequest dataRequest){
        return studentService.addOrUpdateStudent(JsonUtil.prase(dataRequest.get("student"), Student.class));
    }

    @PostMapping("/findByUserId")
    public DataResponse findByUserId(@RequestBody DataRequest dataRequest){
        return studentService.findByUserId(JsonUtil.prase(dataRequest.get("user"), User.class));
    }

    @PostMapping("/getStudentList")
    public DataResponse getStudentList(){
        return studentService.getStudentList();
    }
}
