package com.example.courseprogram.controller;

import com.example.courseprogram.model.DO.User;
import com.example.courseprogram.model.DTO.DataRequest;
import com.example.courseprogram.model.DTO.DataResponse;
import com.example.courseprogram.service.UserService;
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
        return userService.addUser(dataRequest);
    }

    //删除user
    @PostMapping("/deleteUser")
    public DataResponse deleteUser(@RequestBody DataRequest dataRequest){
        return userService.deleteUser(dataRequest);
    }

    //登录
    @PostMapping("/login")
    public DataResponse login(@RequestBody DataRequest dataRequest){
        return userService.login(dataRequest);
    }

    //修改密码
    @PostMapping("/updatePassword")
    public DataResponse updatePassword(@RequestBody DataRequest dataRequest){
        return userService.updatePassword(dataRequest);
    }

    @PostMapping("/sduLogin")
    public DataResponse sduLogin(@RequestBody DataRequest dataRequest){
        return userService.sduLogin(dataRequest);
    }

}
