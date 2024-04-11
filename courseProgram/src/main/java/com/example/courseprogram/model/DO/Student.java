package com.example.courseprogram.model.DO;

import com.example.courseprogram.model.DTO.StudentInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




/**
 * Student学生表实体类 保存每个学生的信息，
 * Integer studentId 用户表 student 主键 student_id
 * Person person 关联到该用户所用的Person对象，账户所对应的人员信息 person_id 关联 person 表主键 person_id
 * String major 专业
 * String className 班级
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@TableName("student")
@Table(name = "student")
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentId;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "person_id")
    private Person person;

    private String major;

    private String className;

    public Student(StudentInfo studentInfo){
        major=studentInfo.getMajor();
        className=studentInfo.getClassName();
    }
}
