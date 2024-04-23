package com.example.courseprogram.controller;

import com.example.courseprogram.model.DO.InnovativePractice;
import com.example.courseprogram.model.DTO.DataRequest;
import com.example.courseprogram.model.DTO.DataResponse;
import com.example.courseprogram.service.InnovativePracticeService;
import com.example.courseprogram.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/innovativePractice")
public class InnovativePracticeController {
    @Autowired
    InnovativePracticeService innovativePracticeService;

    //增加或者修改数据
    @PostMapping("/add")
    public DataResponse addAndUpdInnovativePractice(@RequestBody DataRequest dataRequest){
        return innovativePracticeService.addAndUpdInnovativePractice(JsonUtil.parse(dataRequest.get("innovativePractice"), InnovativePractice.class));
    }


    //删除某学生的所有信息
    @PostMapping("/delete")
    public DataResponse deleteByStudentId(@RequestBody DataRequest dataRequest){
        return innovativePracticeService.deleteByStudentId(JsonUtil.parse(dataRequest.get("id"), Integer.class));
    }

    //查找某学生的信息
    @PostMapping("/findByStudent")
    public DataResponse findByStudentId(@RequestBody DataRequest dataRequest){
        return innovativePracticeService.findByStudentId(JsonUtil.parse(dataRequest.get("id"), Integer.class));
    }

    //获取所有数据
    @PostMapping("/findAll")
    public DataResponse findAll(){
        return innovativePracticeService.findAll();
    }

}
