package com.example.courseprogram.controller;

import com.example.courseprogram.model.DO.HonorInfo;
import com.example.courseprogram.model.DTO.DataRequest;
import com.example.courseprogram.model.DTO.DataResponse;
import com.example.courseprogram.service.HonorInfoService;
import com.example.courseprogram.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/honorInfo")
public class HonorInfoController {
    @Autowired
    HonorInfoService honorInfoService;

    //增加或者修改数据
    @PostMapping("/add")
    public DataResponse addAndUpdHonorInfo(@RequestBody DataRequest dataRequest){
        return honorInfoService.addAndUpdHonorInfo(JsonUtil.parse(dataRequest.get("honorInfo"), HonorInfo.class));
    }

    //根据id删除
    @PostMapping("/deleteById")
    public DataResponse deleteById(@RequestBody DataRequest dataRequest){
        return honorInfoService.deleteById(JsonUtil.parse(dataRequest.get("id"),Integer.class));
    }

    //删除某学生的所有信息
    @PostMapping("/deleteByStudent")
    public DataResponse deleteByStudentId(@RequestBody DataRequest dataRequest){
        return honorInfoService.deleteByStudentId(JsonUtil.parse(dataRequest.get("id"), Long.class));
    }

    //查找某学生的信息
    @PostMapping("/findByStudent")
    public DataResponse findByStudentId(@RequestBody DataRequest dataRequest){
        return honorInfoService.findByStudentId(JsonUtil.parse(dataRequest.get("id"), Long.class));
    }

    //根据名称查找
    @PostMapping("/findByName")
    public DataResponse findByName(@RequestBody DataRequest dataRequest){
        return honorInfoService.findByName(JsonUtil.parse(dataRequest.get("name"), String.class));
    }

    //根据类型查找
    @PostMapping("/findHonorInfosByType")
    public DataResponse findHonorInfosByType(@RequestBody DataRequest dataRequest){
        return honorInfoService.findHonorInfosByType(JsonUtil.parse(dataRequest.get("type"), String.class));
    }

    //获取所有数据
    @PostMapping("/findAll")
    public DataResponse findAll(){
        return honorInfoService.findAll();
    }

    //根据学号和荣誉名称查询
    @PostMapping("/findByStudentIdAndHonorName")
    public DataResponse findByStudentIdAndHonorName(@RequestBody DataRequest dataRequest){
        return honorInfoService.findByStudentIdAndHonorName(JsonUtil.parse(dataRequest.get("id"),Long.class),JsonUtil.parse(dataRequest.get("name"), String.class));
    }

}
