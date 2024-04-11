package com.example.courseprogram.model.DO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * Person人员表实体类 保存人员的基本信息信息， 账户、学生和教师都关联人员，
 * Integer personId 人员表 person 主键 person_id
 * String number 人员编号
 * String name 人员名称
 * String type 人员类型  0管理员 1学生 2教师
 * String dept 学院
 * String card 身份证号
 * String gender 性别  1 男 2 女
 * String birthday 出生日期
 * String email 邮箱
 * String phone 电话
 * String address 地址
 * String introduce 个人简介
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@TableName("person")
@Table(name = "person")
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer personId;

    private String number;

    private String name;

    private String type;

    private String dept;

    private String card;

    private String gender;

    private String birthday;

    private String email;

    private String phone;

    private String address;

    private String introduce;

}
