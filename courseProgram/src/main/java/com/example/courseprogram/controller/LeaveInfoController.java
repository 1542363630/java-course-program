package com.example.courseprogram.controller;

import com.example.courseprogram.model.DO.LeaveInfo;
import com.example.courseprogram.model.DTO.DataRequest;
import com.example.courseprogram.model.DTO.DataResponse;
import com.example.courseprogram.service.LeaveInfoService;
import com.example.courseprogram.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/leaveInfo")
public class LeaveInfoController {
    @Autowired
    LeaveInfoService leaveInfoService;

    //增加或者修改数据
    @PostMapping("/add")
    public DataResponse addAndUpdLeaveInfo(@RequestBody DataRequest dataRequest){
        return leaveInfoService.addAndUpdLeaveInfo(JsonUtil.parse(dataRequest.get("leaveInfo"), LeaveInfo.class));
    }

    //根据id删除
    @PostMapping("/deleteById")
    public DataResponse deleteById(@RequestBody DataRequest dataRequest){
        return leaveInfoService.deleteById(JsonUtil.parse(dataRequest.get("id"),Integer.class));
    }

    //删除某学生的所有请假信息
    @PostMapping("/deleteByStudent")
    public DataResponse deleteByStudentId(@RequestBody DataRequest dataRequest){
        return leaveInfoService.deleteByStudentId(JsonUtil.parse(dataRequest.get("id"), Long.class));
    }

    //查找某学生的请假信息
    @PostMapping("/findByStudent")
    public DataResponse findByStudentId(@RequestBody DataRequest dataRequest){
        return leaveInfoService.findByStudentId(JsonUtil.parse(dataRequest.get("id"), Long.class));
    }

    //根据是否回到学校查找
    @PostMapping("/findByIsBack")
    public DataResponse findByIsBack(@RequestBody DataRequest dataRequest){
        return leaveInfoService.findByIsBack(JsonUtil.parse(dataRequest.get("isBack"), String.class));
    }

    //获取所有数据
    @PostMapping("/findAll")
    public DataResponse findAll(){
        return leaveInfoService.findAll();
    }

}
