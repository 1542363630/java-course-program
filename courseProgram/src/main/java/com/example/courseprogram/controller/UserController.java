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

    @PostMapping("/addUser")
    public DataResponse addUser(@RequestBody DataRequest dataRequest){
        return userService.addUser(dataRequest);
    }

    @PostMapping("/deleteUser")
    public DataResponse deleteUser(@RequestBody DataRequest dataRequest){
        return userService.deleteUser(dataRequest);
    }

    @PostMapping("/login")
    public DataResponse login(@RequestBody DataRequest dataRequest){
        return userService.login(dataRequest);
    }

    @PostMapping("/updatePassword")
    public DataResponse updatePassword(@RequestBody DataRequest dataRequest){
        return userService.updatePassword(dataRequest);
    }

}
