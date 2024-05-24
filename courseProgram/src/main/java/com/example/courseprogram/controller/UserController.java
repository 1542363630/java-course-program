package com.example.courseprogram.controller;

import com.example.courseprogram.Exception.ValidMapValues;
import com.example.courseprogram.model.DO.User;
import com.example.courseprogram.model.DTO.DataRequest;
import com.example.courseprogram.model.DTO.DataResponse;
import com.example.courseprogram.service.UserService;
import com.example.courseprogram.utils.JsonUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    //添加user
    @PostMapping("/addUser")
    public DataResponse addUser(@RequestBody DataRequest dataRequest){
        return userService.addUser(JsonUtil.parse(dataRequest.get("user"), User.class),dataRequest.getString("userName"),dataRequest.getString("userType"));
    }

    //删除user
    @PostMapping("/deleteUser")
    public DataResponse deleteUser(@RequestBody DataRequest dataRequest){
        return userService.deleteUser(JsonUtil.parse(dataRequest.get("userId"), Integer.class));
    }

    //登录
    @PostMapping("/login")
    public DataResponse login(@RequestBody DataRequest dataRequest){
        return userService.login(JsonUtil.parse(dataRequest.get("user"),User.class));
    }

    //修改密码
    @PostMapping("/updatePassword")
    public DataResponse updatePassword(@RequestBody DataRequest dataRequest){
        return userService.updatePassword(JsonUtil.parse(dataRequest.get("user"), User.class));
    }

    //根据personId查找
    @PostMapping("/findByPersonId")
    public DataResponse findByPersonId(@RequestBody DataRequest dataRequest){
        return userService.findByPersonId(JsonUtil.parse(dataRequest.get("personId"), Integer.class));
    }

    //根据 userType 查找
    @PostMapping("/findByUserType")
    public DataResponse findByUserType(@RequestBody DataRequest dataRequest){
        return userService.findByUserType(JsonUtil.parse(dataRequest.get("type"), String.class));
    }

    //山大统一认证登录
    @PostMapping("/sduLogin")
    public DataResponse sduLogin(@RequestBody DataRequest dataRequest){
        return userService.sduLogin(JsonUtil.parse(dataRequest.get("user"), User.class));
    }

}
