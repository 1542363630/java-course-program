package com.example.courseprogram.model.DO;

import com.example.courseprogram.model.DTO.StudentInfo;
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
@Table(name = "student")
@Entity
public class Student implements Serializable {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;

    private String major;

    private String className;

    private String GPA;

    public Student(StudentInfo studentInfo){
        studentId = Long.valueOf(studentInfo.getNumber());
        major = studentInfo.getMajor();
        className = studentInfo.getClassName();
        GPA=studentInfo.getGPA();
    }
}
