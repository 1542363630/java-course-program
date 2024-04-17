package com.example.courseprogram.model.DO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>Course 课程表实体类  保存课程的的基本信息信息，
 * <p>Integer courseId 主键 course_id
 * <p>String number 课程编号
 * <p>String name 课程名称
 * <p>Integer credit 学分
 * <p>String courseTime 课程时间（一天中的时间）
 * <p>String teacherName 授课老师
 * <p>String location 上课地点
 * <p>Course preCourse 前序课程 pre_course_id 关联前序课程的主键 course_id
 * <p>String courseBeginWeek 课程开始于第几周
 * <p>String courseEndWeek 课程结束于第几周
 * <p>String wayOfTest 考核方式
 * <p>String type 课程类型
 * <p>Integer numberOfSelected 选课人数
 * <p> Integer MaxNumberOfSelected 课程容量
 * <p>
 * <p>
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "course")
@Entity
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer courseId;
    private String number;
    private String name;
    private Integer credit;
    private String courseTime;
    private String courseBeginWeek;
    private String courseEndWeek;
    private String wayOfTest;
    private String teacherName;
    private String location;
    @ManyToOne
    @JoinColumn(name = "pre_course_id")
    private Course preCourse;
    private String type;

}
