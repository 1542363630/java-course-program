package com.example.courseprogram.controller;

import com.example.courseprogram.model.DO.Person;
import com.example.courseprogram.model.DTO.DataRequest;
import com.example.courseprogram.model.DTO.DataResponse;
import com.example.courseprogram.service.PersonService;
import com.example.courseprogram.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/person")
public class PersonController {
    @Autowired
    PersonService personService;

    //增加或者修改数据
    @PostMapping("/addOrUpd")
    public DataResponse addOrUpdatePerson(@RequestBody DataRequest dataRequest){
        return personService.addOrUpdatePerson(JsonUtil.prase(dataRequest.get("person"), Person.class));
    }

    //增加数据
    @PostMapping("/add")
    public DataResponse addPerson(@RequestBody DataRequest dataRequest){
        return personService.addPerson(JsonUtil.prase(dataRequest.get("person"), Person.class));
    }


    //删除某人的个人信息
    @PostMapping("/delete")
    public DataResponse deleteByPersonNumber(@RequestBody DataRequest dataRequest){
        return personService.deleteByPersonNumber(JsonUtil.prase(dataRequest.get("number"), String.class));
    }

    //根据用户名查找个人信息
    @PostMapping("/findByUserName")
    public DataResponse findByUserName(@RequestBody DataRequest dataRequest){
        return personService.findByUserName(JsonUtil.prase(dataRequest.get("name"), String.class));
    }


    //获取所有数据
    @PostMapping("/findAll")
    public DataResponse findAll(){
        return personService.findAll();
    }


}
