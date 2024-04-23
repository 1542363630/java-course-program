package com.example.courseprogram.controller;

import com.example.courseprogram.model.DO.SocietyMember;
import com.example.courseprogram.model.DTO.DataRequest;
import com.example.courseprogram.model.DTO.DataResponse;
import com.example.courseprogram.service.SocietyMemberService;
import com.example.courseprogram.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/societyMember")
public class SocietyMemberController {
    @Autowired
    SocietyMemberService societyMemberService;

    //增加或者修改数据
    @PostMapping("/add")
    public DataResponse addAndUpdSocietyMember(@RequestBody DataRequest dataRequest){
        return societyMemberService.addAndUpdSocietyMember(JsonUtil.parse(dataRequest.get("societyMember"), SocietyMember.class));
    }


    //删除某学生的所有信息
    @PostMapping("/delete")
    public DataResponse deleteByStudentId(@RequestBody DataRequest dataRequest){
        return societyMemberService.deleteByStudentId(JsonUtil.parse(dataRequest.get("id"), Integer.class));
    }

    //查找某学生的信息
    @PostMapping("/findByStudent")
    public DataResponse findByStudentId(@RequestBody DataRequest dataRequest){
        return societyMemberService.findByStudentId(JsonUtil.parse(dataRequest.get("id"), Integer.class));
    }

    //获取所有数据
    @PostMapping("/findAll")
    public DataResponse findAll(){
        return societyMemberService.findAll();
    }

}
