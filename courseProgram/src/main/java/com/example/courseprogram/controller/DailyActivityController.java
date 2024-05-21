package com.example.courseprogram.controller;

import com.alibaba.fastjson2.JSON;
import com.example.courseprogram.model.DO.DailyActivity;
import com.example.courseprogram.model.DO.Student;
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
        return dailyActivityService.addAndUpdDailyActivity(JsonUtil.parse(dataRequest.get("dailyActivity"), DailyActivity.class), JSON.parseArray(JSON.toJSONString(dataRequest.get("students")), Student.class));
    }

    //根据id删除
    @PostMapping("/deleteById")
    public DataResponse deleteById(@RequestBody DataRequest dataRequest){
        return dailyActivityService.deleteById(JsonUtil.parse(dataRequest.get("id"),Integer.class));
    }

    //根据学号和活动类型查询
    @PostMapping("/findByStudentIdAndType")
    public DataResponse findByStudentIdAndType(@RequestBody DataRequest dataRequest){
        return dailyActivityService.findByStudentIdAndType(JsonUtil.parse(dataRequest.get("id"),Long.class),JsonUtil.parse(dataRequest.get("type"), String.class));
    }

    //查找某学生的信息
    @PostMapping("/findByStudent")
    public DataResponse findByStudentId(@RequestBody DataRequest dataRequest){
        return dailyActivityService.findByStudentId(JsonUtil.parse(dataRequest.get("id"), Long.class));
    }

//    //根据类别查找
//    @PostMapping("/findByType")
//    public DataResponse findByType(@RequestBody DataRequest dataRequest){
//        return dailyActivityService.findByType(JsonUtil.parse(dataRequest.get("type"), String.class));
//    }

    //根据名称查找
    @PostMapping("/findByName")
    public DataResponse findByName(@RequestBody DataRequest dataRequest){
        return dailyActivityService.findByName(JsonUtil.parse(dataRequest.get("name"), String.class));
    }

    //获取所有数据
    @PostMapping("/findAll")
    public DataResponse findAll(){
        return dailyActivityService.findAll();
    }

}
