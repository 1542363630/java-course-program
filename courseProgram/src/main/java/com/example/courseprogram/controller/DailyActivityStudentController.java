package com.example.courseprogram.controller;

import com.example.courseprogram.model.DO.DailyActivityStudent;
import com.example.courseprogram.model.DTO.DataRequest;
import com.example.courseprogram.model.DTO.DataResponse;
import com.example.courseprogram.service.DailyActivityStudentService;
import com.example.courseprogram.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dailyActivityStudent")
public class DailyActivityStudentController {
    @Autowired
    DailyActivityStudentService dailyActivityStudentService;

    //增加或者修改数据
    @PostMapping("/add")
    public DataResponse addAndUpdDailyActivityStudent(@RequestBody DataRequest dataRequest){
        return dailyActivityStudentService.addAndUpdDailyActivityStudent(JsonUtil.parse(dataRequest.get("dailyActivityStudent"), DailyActivityStudent.class));
    }

    //根据id删除
    @PostMapping("/deleteById")
    public DataResponse deleteById(@RequestBody DataRequest dataRequest){
        return dailyActivityStudentService.deleteById(JsonUtil.parse(dataRequest.get("id"),Integer.class));
    }

    //查找某学生的信息
    @PostMapping("/findByStudent")
    public DataResponse findByStudentId(@RequestBody DataRequest dataRequest){
        return dailyActivityStudentService.findByStudentId(JsonUtil.parse(dataRequest.get("id"), Long.class));
    }
    
    //删除某学生的所有信息
    @PostMapping("/deleteByStudent")
    public DataResponse deleteByStudentId(@RequestBody DataRequest dataRequest){
        return dailyActivityStudentService.deleteByStudentId(JsonUtil.parse(dataRequest.get("id"), Long.class));
    }

    //查找某活动的信息
    @PostMapping("/findByDailyActivity")
    public DataResponse findByDailyActivityId(@RequestBody DataRequest dataRequest){
        return dailyActivityStudentService.findByDailyActivityId(JsonUtil.parse(dataRequest.get("id"), Integer.class));
    }

    //删除某活动的所有信息
    @PostMapping("/deleteByDailyActivity")
    public DataResponse deleteByDailyActivityId(@RequestBody DataRequest dataRequest){
        return dailyActivityStudentService.deleteByDailyActivityId(JsonUtil.parse(dataRequest.get("id"), Integer.class));
    }

    //获取所有数据
    @PostMapping("/findAll")
    public DataResponse findAll(){
        return dailyActivityStudentService.findAll();
    }

}
