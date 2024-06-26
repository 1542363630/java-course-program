package com.example.courseprogram.model.DO;

import com.example.courseprogram.Exception.AllowedValues;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

/**
 * <p>Person人员表实体类 保存人员的基本信息信息， 账户、学生和教师都关联人员，
 * <p>Integer personId 人员表 person 主键 person_id
 * <p>String number 人员编号
 * <p>String name 人员名称
 * <p>String type 人员类型  0管理员 1学生 2教师
 * <p>String dept 学院
 * <p>String card 身份证号
 * <p>String gender 性别  1 男 2 女
 * <p>String birthday 出生日期
 * <p>String email 邮箱
 * <p>String phone 电话
 * <p>String address 地址
 * <p>String introduce 个人简介
 * <p>String politicalStatus 政治面貌
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "person")
@Entity
public class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer personId;
    @NotBlank(message = "学工号不能为空")
    private String number;
    @NotBlank(message = "姓名不能为空")
    private String name;
    @NotBlank(message = "用户类型不能为空")
    @AllowedValues(allowedValues = {"admin","student","teacher"},message = "用户类型必须为(admin,student,teacher)中的一个")
    private String type;

    private String dept;

    private String card;

    private String gender;

    private String birthday;

    private String email;

    private String phone;

    private String address;

    private String introduce;
    /**
     * 政治面貌：
     * <p>群众、共青团员、共产党员、其它</p>
     */
    private String politicalStatus;
}
