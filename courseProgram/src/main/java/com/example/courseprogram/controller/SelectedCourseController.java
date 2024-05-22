package com.example.courseprogram.controller;

import com.alibaba.fastjson2.JSON;
import com.example.courseprogram.model.DO.SelectedCourse;
import com.example.courseprogram.model.DO.SelectedCourseInfo;
import com.example.courseprogram.model.DO.Student;
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
    public DataResponse addAndUpdSelectedCourse(@RequestBody DataRequest dataRequest){
        return selectedCourseService.addAndUpdSelectedCourse(JsonUtil.parse(dataRequest.get("selectedCourse"), SelectedCourse.class));
    }

    //学生选课的增加和修改
    @PostMapping("/addByStudentAndList")
    public DataResponse addByStudentAndList(@RequestBody DataRequest dataRequest){
        return selectedCourseService.addByStudentAndList(JsonUtil.parse(dataRequest.get("student"), Student.class), JSON.parseArray(JSON.toJSONString(dataRequest.get("selectedCourseInfos")), SelectedCourseInfo.class));
    }

    //根据id删除
    @PostMapping("/deleteById")
    public DataResponse deleteById(@RequestBody DataRequest dataRequest){
        return selectedCourseService.deleteById(JsonUtil.parse(dataRequest.get("id"),Integer.class));
    }

    //删除某学生的所有选课数据
    @PostMapping("/deleteByStudentId")
    public DataResponse deleteByStudentId(@RequestBody DataRequest dataRequest){
        return selectedCourseService.deleteByStudentId(JsonUtil.parse(dataRequest.get("id"), Long.class));
    }

    //删除某课程的所有选课数据
    @PostMapping("/deleteByCourseId")
    public DataResponse deleteByCourseId(@RequestBody DataRequest dataRequest){
        return selectedCourseService.deleteByCourseId(JsonUtil.parse(dataRequest.get("id"), Integer.class));
    }

    //查找某学生的所有选课数据
    @PostMapping("/findByStudentId")
    public DataResponse findByStudentId(@RequestBody DataRequest dataRequest){
        return selectedCourseService.findByStudentId(JsonUtil.parse(dataRequest.get("id"), Long.class));
    }

    //查找某课程的所有选课数据
    @PostMapping("/findByCourseId")
    public DataResponse findByCourseId(@RequestBody DataRequest dataRequest){
        return selectedCourseService.findByCourseId(JsonUtil.parse(dataRequest.get("id"), Integer.class));
    }


    //根据学号和课程种类查询
    @PostMapping("/findByStudentIdAndCourseType")
    public DataResponse findByStudentIdAndCourseType(@RequestBody DataRequest dataRequest){
        return selectedCourseService.findByStudentIdAndCourseType(JsonUtil.parse(dataRequest.get("studentId"), Long.class),JsonUtil.parse(dataRequest.get("courseType"), String.class));
    }

    //根据学号和(课程编号或名称)查询
    @PostMapping("/findByStudentIdAndNumName")
    public DataResponse findByStudentIdAndNumName(@RequestBody DataRequest dataRequest){
        return selectedCourseService.findByStudentIdAndNumName(JsonUtil.parse(dataRequest.get("studentId"), Long.class),JsonUtil.parse(dataRequest.get("numName"), String.class));
    }

    //根据学号和教师名称
    @PostMapping("/findByStudentIdAndTeacherName")
    public DataResponse findByStudentIdAndTeacherName(@RequestBody DataRequest dataRequest){
        return selectedCourseService.findByStudentIdAndTeacherName(JsonUtil.parse(dataRequest.get("studentId"), Long.class),JsonUtil.parse(dataRequest.get("teacherName"), String.class));
    }


    //获取所有数据
    @PostMapping("/findAll")
    public DataResponse findAll(){
        return selectedCourseService.findAll();
    }

}
