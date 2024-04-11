package com.example.courseprogram.service;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.example.courseprogram.model.DO.User;
import com.example.courseprogram.model.DTO.DataRequest;
import com.example.courseprogram.model.DTO.DataResponse;
import com.example.courseprogram.model.DTO.Token;
import com.example.courseprogram.model.DTO.UserInfo;
import com.example.courseprogram.repository.UserRepository;
import com.example.courseprogram.utils.JsonUtil;
import com.example.courseprogram.utils.JwtUtil;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService{

    @Autowired
    UserRepository userRepository;

    public DataResponse addUser(DataRequest dataRequest){
        User user = new User();
        user=(User) dataRequest.get("user");
        user.setUserName(dataRequest.getString("userName"));
        user.setUserType(dataRequest.getString("userType"));


        User userExist = userRepository.findUserByUserName(user.getUserName());
        if(userExist != null){
            return DataResponse.failure(400,"该用户已存在！");
        }
        String encodedPassword = BCrypt.hashpw(user.getPassword(),BCrypt.gensalt());  //密码加密
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return DataResponse.ok();
    }

    public DataResponse deleteUser(DataRequest dataRequest){
        User user=JsonUtil.prase(dataRequest.get("user"), User.class);
        User userExist = userRepository.findUserByUserName(user.getUserName());
        if(userExist != null){
            return DataResponse.failure(400,"该用户不存在！");
        }
        userRepository.deleteById(user.getUserId());
        return DataResponse.ok();
    }

    public DataResponse login(DataRequest dataRequest){
//        User user1 = JSONObject.parseObject(JSON.toJSONString(dataRequest.get("user")),User.class);
        User user1 = JsonUtil.prase(dataRequest.get("user"),User.class);
        String userName= String.valueOf(user1.getUserName());
        String password=String.valueOf(user1.getPassword());
        User user= userRepository.findUserByUserName(userName);
        if(user == null){
            return DataResponse.notFound();
        }
        else {
            //String encodedPassword = BCrypt.hashpw(password,BCrypt.gensalt());
            UserInfo userInfo = new UserInfo(user);
            if(BCrypt.checkpw(password,user.getPassword())){
                return DataResponse.success(new Token(JwtUtil.generateToken(userName),userInfo));
            }
            else {
                return DataResponse.failure(401,"密码错误！");
            }
        }
    }

    public DataResponse updatePassword(DataRequest dataRequest){
        User userRe = JsonUtil.prase(dataRequest.get("user"), User.class);
        String userName = userRe.getUserName();
        String newPassword = userRe.getPassword();
        User user = userRepository.findUserByUserName(userName);
        if(user!=null){
            String encodedPassword = BCrypt.hashpw(newPassword,BCrypt.gensalt());
            user.setPassword(encodedPassword);
            userRepository.saveAndFlush(user);
            return DataResponse.ok();
        }
        else {
            return DataResponse.notFound();
        }

    }


}
