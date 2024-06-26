package com.example.courseprogram.model.DTO;

import com.example.courseprogram.model.DO.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private String userId;
    private String userType;
    private String userName;

    public UserInfo(User user){
        userId= String.valueOf(user.getUserId());
        userName=user.getUserName();
        userType=user.getUserType();
    }

    public UserInfo(String userId){
        this.userId= userId;
        this.userType="user";
    }
}
