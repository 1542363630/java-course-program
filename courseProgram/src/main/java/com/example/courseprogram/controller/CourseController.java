package com.example.courseprogram.controller;

import com.example.courseprogram.model.DO.Course;
import com.example.courseprogram.model.DTO.DataRequest;
import com.example.courseprogram.model.DTO.DataResponse;
import com.example.courseprogram.service.CourseService;
import com.example.courseprogram.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/course")
public class CourseController {
    @Autowired
    CourseService courseService;

    //增加或者修改数据
    @PostMapping("/add")
    public DataResponse addAndUpdCourse(@RequestBody DataRequest dataRequest){
        return courseService.addAndUpdCourse(JsonUtil.prase(dataRequest.get("course"), Course.class));
    }


    //删除课程
    @PostMapping("/delete")
    public DataResponse deleteByCourseId(@RequestBody DataRequest dataRequest){
        return courseService.deleteByCourseId(JsonUtil.prase(dataRequest.get("id"), Integer.class));
    }

    //根据课程编号查找课程的数据
    @PostMapping("/findByCourseNumber")
    public DataResponse findByCourseNumber(@RequestBody DataRequest dataRequest){
        return courseService.findByCourseNumber(JsonUtil.prase(dataRequest.get("number"), String.class));
    }

    //根据课程名字查找课程的数据
    @PostMapping("/findByCourseName")
    public DataResponse findByCourseName(@RequestBody DataRequest dataRequest){
        return courseService.findByCourseName(JsonUtil.prase(dataRequest.get("name"), String.class));
    }

    //获取所有数据
    @PostMapping("/findAll")
    public DataResponse findAll(){
        return courseService.findAll();
    }

}
