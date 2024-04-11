package com.example.courseprogram.model.DO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Course 课程表实体类  保存课程的的基本信息信息，
 * Integer courseId 人员表 course 主键 course_id
 * String num 课程编号
 * String name 课程名称
 * Integer credit 学分
 * Course preCourse 前序课程 pre_course_id 关联前序课程的主键 course_id
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@TableName("course")
@Table(name = "course")
@Entity
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer courseId;
    private String num;
    private String name;
    private Integer credit;
    @ManyToOne
//    @TableField("pre_course_id")
    @JoinColumn(name = "pre_course_id")
    private Course preCourse;
    private String coursePath;
}
