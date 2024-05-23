package com.example.courseprogram.controller;

import com.example.courseprogram.model.DO.HomeworkInfo;
import com.example.courseprogram.model.DO.SelectedCourseInfo;
import com.example.courseprogram.model.DTO.DataRequest;
import com.example.courseprogram.model.DTO.DataResponse;
import com.example.courseprogram.service.HomeworkInfoService;
import com.example.courseprogram.service.SelectedCourseInfoService;
import com.example.courseprogram.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/selectedCourseInfo")
public class SelectedCourseInfoController {
    @Autowired
    SelectedCourseInfoService selectedCourseInfoService;

    //增加或者修改数据
    @PostMapping("/add")
    public DataResponse addAndUpdSelectedCourseInfo(@RequestBody DataRequest dataRequest){
        return selectedCourseInfoService.addOrUpdSelectedCourseInfo(JsonUtil.parse(dataRequest.get("selectedCourseInfo"), SelectedCourseInfo.class));
    }

    //根据id删除
    @PostMapping("/deleteById")
    public DataResponse deleteById(@RequestBody DataRequest dataRequest){
        return selectedCourseInfoService.deleteById(JsonUtil.parse(dataRequest.get("id"),Integer.class));
    }

    //根据课程id查找
    @PostMapping("/findByCourseId")
    public DataResponse findByCourseId(@RequestBody DataRequest dataRequest){
        return selectedCourseInfoService.findByCourseId(JsonUtil.parse(dataRequest.get("id"),Integer.class));
    }

    //根据课程id删除
    @PostMapping("/deleteByCourseId")
    public DataResponse deleteByCourseId(@RequestBody DataRequest dataRequest){
        return selectedCourseInfoService.deleteByCourseId(JsonUtil.parse(dataRequest.get("id"),Integer.class));
    }

    //查找指定id的信息
    @PostMapping("/findById")
    public DataResponse findById(@RequestBody DataRequest dataRequest){
        return selectedCourseInfoService.findById(JsonUtil.parse(dataRequest.get("id"),Integer.class));
    }

    //根据课程编号或名称查询
    @PostMapping("/findByCourseNumberOrName")
    public DataResponse findByCourseNumberOrName(@RequestBody DataRequest dataRequest){
        return selectedCourseInfoService.findByCourseNumberOrName(JsonUtil.parse(dataRequest.get("numName"), String.class));
    }

    //根据教师名称查询
    @PostMapping("/findByTeacherName")
    public DataResponse findByTeacherName(@RequestBody DataRequest dataRequest){
        return selectedCourseInfoService.findByTeacherName(JsonUtil.parse(dataRequest.get("teacherName"), String.class));
    }

    //获取所有数据
    @PostMapping("/findAll")
    public DataResponse findAll(){
        return selectedCourseInfoService.findAll();
    }

}
