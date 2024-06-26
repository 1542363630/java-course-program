package com.example.courseprogram.model.DO;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

/**
 * <p>Student学生表实体类 保存每个学生的信息，
 * <p>Integer studentId 用户表 student 主键 student_id
 * <p>Person person 关联到该用户所用的Person对象，账户所对应的人员信息 person_id 关联 person 表主键 person_id
 * <p>String major 专业
 * <p>String className 班级
 *
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "teacher")
@Entity
public class Teacher implements Serializable {
    @Id
    private Long teacherId;

    @OneToOne
    @JoinColumn(name = "person_id")
//    @TableField("person_id")
    private Person person;

    private String title;

    private String degree;

}
