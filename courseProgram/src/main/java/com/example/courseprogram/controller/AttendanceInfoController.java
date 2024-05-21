package com.example.courseprogram.controller;

import com.example.courseprogram.model.DO.AttendanceInfo;
import com.example.courseprogram.model.DTO.DataRequest;
import com.example.courseprogram.model.DTO.DataResponse;
import com.example.courseprogram.service.AttendanceInfoService;
import com.example.courseprogram.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceInfoController {

    @Autowired
    AttendanceInfoService attendanceInfoService;

    //增加或者修改数据
    @PostMapping("/add")
    public DataResponse addAndUpdAttendanceInfo(@RequestBody DataRequest dataRequest){
        return attendanceInfoService.addAndUpdAttendanceInfo(JsonUtil.parse(dataRequest.get("attendanceInfo"), AttendanceInfo.class));
    }

    //根据id删除
    @PostMapping("/deleteById")
    public DataResponse deleteById(@RequestBody DataRequest dataRequest){
        return attendanceInfoService.deleteById(JsonUtil.parse(dataRequest.get("id"),Integer.class));
    }

    //删除某学生的所有考勤信息
    @PostMapping("/deleteByStudent")
    public DataResponse deleteByStudentId(@RequestBody DataRequest dataRequest){
        return attendanceInfoService.deleteByStudentId(JsonUtil.parse(dataRequest.get("id"), Long.class));
    }

    //查找某学生的考勤信息
    @PostMapping("/findByStudent")
    public DataResponse findByStudentId(@RequestBody DataRequest dataRequest){
        return attendanceInfoService.findByStudentId(JsonUtil.parse(dataRequest.get("id"), Long.class));
    }

    //根据是否考勤查找
    @PostMapping("/findByIsAttended")
    public DataResponse findByIsAttended(@RequestBody DataRequest dataRequest){
        return attendanceInfoService.findByIsAttended(JsonUtil.parse(dataRequest.get("isAttended"), String.class));
    }

    //根据类型查询
    @PostMapping("/findAttendanceInfoByType")
    public DataResponse findAttendanceInfoByType(@RequestBody DataRequest dataRequest){
        return attendanceInfoService.findAttendanceInfoByType(JsonUtil.parse(dataRequest.get("type"), String.class));
    }

//    //查找某课程的考勤信息
//    @PostMapping("/findByCourse")
//    public DataResponse findByCourseId(@RequestBody DataRequest dataRequest){
//        return attendanceInfoService.findByCourseId(JsonUtil.prase(dataRequest.get("id"), Integer.class));
//    }

    //获取所有数据
    @PostMapping("/findAll")
    public DataResponse findAll(){
        return attendanceInfoService.findAll();
    }


}
