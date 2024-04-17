package com.example.courseprogram.model.DO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>InnovativePractice 创新实践信息类
 * <p>Integer innovativeId 主键id
 * <p>Student student 对应的学生
 * <p>String teacherName 指导老师的姓名
 * <p>String achievement 成果
 * <p>String beginTime 开始时间
 * <p>String endTime 结束时间
 * <p>String type 类型
 * <p>String file 文件路径
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "innovative_practice")
@Entity
public class InnovativePractice implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer innovativeId;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    private String activityName;

    private String teacherName;

    private String achievement;

    private String beginTime;

    private String endTime;

    private String type;

    private String file;
}
