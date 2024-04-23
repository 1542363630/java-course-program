package com.example.courseprogram.controller;

import com.example.courseprogram.model.DO.SelectedCourse;
import com.example.courseprogram.model.DTO.DataRequest;
import com.example.courseprogram.model.DTO.DataResponse;
import com.example.courseprogram.service.SelectedCourseService;
import com.example.courseprogram.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/selectedCourse")
public class SelectedCourseController {
    @Autowired
    SelectedCourseService selectedCourseService;

    //增加或者修改数据
    @PostMapping("/add")
    public DataResponse addAndUpdSelectedCourse(DataRequest dataRequest){
        return selectedCourseService.addAndUpdSelectedCourse(JsonUtil.parse(dataRequest.get("selectedCourse"), SelectedCourse.class));
    }


    //删除某学生的所有选课数据
    @PostMapping("/deleteByStudentId")
    public DataResponse deleteByStudentId(@RequestBody DataRequest dataRequest){
        return selectedCourseService.deleteByStudentId(JsonUtil.parse(dataRequest.get("id"), Integer.class));
    }

    //删除某课程的所有选课数据
    @PostMapping("/deleteByCourseId")
    public DataResponse deleteByCourseId(@RequestBody DataRequest dataRequest){
        return selectedCourseService.deleteByCourseId(JsonUtil.parse(dataRequest.get("id"), Integer.class));
    }

    //查找某学生的所有选课数据
    @PostMapping("/findByStudentId")
    public DataResponse findByStudentId(@RequestBody DataRequest dataRequest){
        return selectedCourseService.findByStudentId(JsonUtil.parse(dataRequest.get("id"), Integer.class));
    }

    //查找某课程的所有选课数据
    @PostMapping("/findByCourseId")
    public DataResponse findByCourseId(@RequestBody DataRequest dataRequest){
        return selectedCourseService.findByCourseId(JsonUtil.parse(dataRequest.get("id"), Integer.class));
    }

    //获取所有数据
    @PostMapping("/findAll")
    public DataResponse findAll(){
        return selectedCourseService.findAll();
    }

}
