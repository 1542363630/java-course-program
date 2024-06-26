package com.example.courseprogram.model.DO;

import com.example.courseprogram.Exception.AllowedValues;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;


/**
 * <p>User用户表实体类 保存每个允许登录的信息人员的账号信息，
 * <p>Integer userId 用户表 user 主键 user_id
 * <p>String userType 关联到用户类型对象
 * <p>Person person 关联到该用户所用的Person对象，账户所对应的人员信息 person_id 关联 person 表主键 person_id
 * <p>String userName 登录账号 和Person 中的num属性相同
 * <p>String password 用户密码 非对称加密，这能加密，无法解码
 *
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    //用户类型 admin student teacher
    @NotBlank(message = "用户类型不能为空")
    @AllowedValues(allowedValues = {"admin","student","teacher"},message = "用户类型必须为(admin,student,teacher)中的一个")
    private String userType;

    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;
    @NotBlank(message = "用户名不能为空")
    @Size(max = 50,message = "用户名不能超过50个字符")
    private String userName;
    @NotBlank(message = "密码不能为空")
    private String password;

    @JsonIgnore
    private Integer loginCount;
    @JsonIgnore
    private String lastLoginTime;
    @JsonIgnore
    private String  createTime;
    @JsonIgnore
    private Integer creatorId;
}
