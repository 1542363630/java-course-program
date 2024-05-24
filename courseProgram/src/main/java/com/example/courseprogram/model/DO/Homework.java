package com.example.courseprogram.model.DO;
import com.example.courseprogram.Exception.AllowedValues;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

/**
 * <p>Homework 作业实体类
 * <p>Integer homeworkId 主键id
 * <p>String isSubmit 提交状态
 * <p>HomeworkInfo homeworkInfo 对应的作业的信息
 * <p>Student student 对应的学生
 * <p>String isChecked 批改状态
 * <p>String checkTime 批改时间
 * <p>String homeworkScore 作业成绩
 * <p>String submitTime 提交时间
 * <p>String file 学生提交的作业文件
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "homework")
@Entity
public class Homework implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer homeworkId;

    @ManyToOne
    @JoinColumn(name = "homework_info_id")
    private HomeworkInfo homeworkInfo;

    @NotBlank(message = "提交状态不能为空")
    @AllowedValues(allowedValues = {"已提交","未提交"},message = "必须为(已提交、未提交)中的一个")
    private String isSubmit;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
    @NotBlank(message = "审核状态不能为空")
    @AllowedValues(allowedValues = {"已审核","未审核","等待提交"},message = "必须为(已审核、未审核、等待提交)中的一个")
    private String isChecked;

    private String checkTime;

    private String submitTime;
    @NotBlank(message = "作业成绩不能为空")
    @DecimalMax(value = "100",message = "成绩不能超过100")
    @DecimalMin(value = "0",message = "成绩不能小于0")
    @Digits(integer = 3,fraction = 0,message = "必须为整数")
    private String homeworkScore;

    private String file;
}
