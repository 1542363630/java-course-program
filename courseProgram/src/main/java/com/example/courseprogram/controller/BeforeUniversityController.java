package com.example.courseprogram.controller;

import com.example.courseprogram.model.DO.BeforeUniversity;
import com.example.courseprogram.model.DTO.DataRequest;
import com.example.courseprogram.model.DTO.DataResponse;
import com.example.courseprogram.service.BeforeUniversityService;
import com.example.courseprogram.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/beforeUniversity")
public class BeforeUniversityController {
    @Autowired
    BeforeUniversityService beforeUniversityService;

    //增加或者修改数据
    @PostMapping("/add")
    public DataResponse addAndUpdBeforeUniversity(@RequestBody DataRequest dataRequest){
        return beforeUniversityService.addAndUpdBeforeUniversity(JsonUtil.parse(dataRequest.get("beforeUniversity"), BeforeUniversity.class));
    }

    //根据id删除
    @PostMapping("/deleteById")
    public DataResponse deleteById(@RequestBody DataRequest dataRequest){
        return beforeUniversityService.deleteById(JsonUtil.parse(dataRequest.get("id"),Integer.class));
    }

    //删除某学生的所有信息
    @PostMapping("/deleteByStudent")
    public DataResponse deleteByStudentId(@RequestBody DataRequest dataRequest){
        return beforeUniversityService.deleteByStudentId(JsonUtil.parse(dataRequest.get("id"), Long.class));
    }

    //查找某学生的信息
    @PostMapping("/findByStudent")
    public DataResponse findByStudentId(@RequestBody DataRequest dataRequest){
        return beforeUniversityService.findByStudentId(JsonUtil.parse(dataRequest.get("id"), Long.class));
    }

    //获取所有数据
    @PostMapping("/findAll")
    public DataResponse findAll(){
        return beforeUniversityService.findAll();
    }
}
