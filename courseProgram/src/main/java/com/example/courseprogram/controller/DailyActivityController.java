package com.example.courseprogram.controller;

import com.example.courseprogram.model.DO.DailyActivity;
import com.example.courseprogram.model.DTO.DataRequest;
import com.example.courseprogram.model.DTO.DataResponse;
import com.example.courseprogram.service.DailyActivityService;
import com.example.courseprogram.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dailyActivity")
public class DailyActivityController {
    @Autowired
    DailyActivityService dailyActivityService;

    //增加或者修改数据
    @PostMapping("/add")
    public DataResponse addAndUpdDailyActivity(@RequestBody DataRequest dataRequest){
        return dailyActivityService.addAndUpdDailyActivity(JsonUtil.parse(dataRequest.get("dailyActivity"), DailyActivity.class));
    }


    //删除某学生的所有信息
    @PostMapping("/delete")
    public DataResponse deleteByStudentId(@RequestBody DataRequest dataRequest){
        return dailyActivityService.deleteByStudentId(JsonUtil.parse(dataRequest.get("id"), Integer.class));
    }

    //查找某学生的信息
    @PostMapping("/findByStudent")
    public DataResponse findByStudentId(@RequestBody DataRequest dataRequest){
        return dailyActivityService.findByStudentId(JsonUtil.parse(dataRequest.get("id"), Integer.class));
    }

    //获取所有数据
    @PostMapping("/findAll")
    public DataResponse findAll(){
        return dailyActivityService.findAll();
    }

}
