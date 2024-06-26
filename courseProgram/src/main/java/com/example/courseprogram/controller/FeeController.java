package com.example.courseprogram.controller;

import com.example.courseprogram.model.DO.Fee;
import com.example.courseprogram.model.DTO.DataRequest;
import com.example.courseprogram.model.DTO.DataResponse;
import com.example.courseprogram.service.FeeService;
import com.example.courseprogram.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fee")
public class FeeController {
    @Autowired
    FeeService feeService;

    //增加或者修改数据
    @PostMapping("/add")
    public DataResponse addAndUpdFee(@RequestBody DataRequest dataRequest){
        return feeService.addAndUpdFee(JsonUtil.parse(dataRequest.get("fee"), Fee.class));
    }

    //根据id删除
    @PostMapping("/deleteById")
    public DataResponse deleteById(@RequestBody DataRequest dataRequest){
        return feeService.deleteById(JsonUtil.parse(dataRequest.get("id"),Integer.class));
    }

    //删除某学生的所有信息
    @PostMapping("/delete")
    public DataResponse deleteByStudentId(@RequestBody DataRequest dataRequest){
        return feeService.deleteByStudentId(JsonUtil.parse(dataRequest.get("id"), Long.class));
    }

    //查找某学生的信息
    @PostMapping("/findByStudent")
    public DataResponse findByStudentId(@RequestBody DataRequest dataRequest){
        return feeService.findByStudentId(JsonUtil.parse(dataRequest.get("id"), Long.class));
    }

    //获取所有数据
    @PostMapping("/findAll")
    public DataResponse findAll(){
        return feeService.findAll();
    }

    //根据学生id和日期查询
    @PostMapping("/findByStudentIdAndDay")
    public DataResponse findByStudentIdAndDay(@RequestBody DataRequest dataRequest){
        return feeService.findByStudentIdAndDay(JsonUtil.parse(dataRequest.get("id"), Long.class), JsonUtil.parse(dataRequest.get("day"), String.class));
    }

}
