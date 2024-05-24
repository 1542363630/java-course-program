package com.example.courseprogram.model.DO;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

/**
 * <p>Score 成绩表实体类  保存成绩的的基本信息信息，
 * <p>Integer scoreId 人员表 score 主键 score_id
 * <p>Student student 关联学生 student_id 关联学生的主键 student_id
 * <p>Course course 关联课程 course_id 关联课程的主键 course_id
 * <p>Integer mark 成绩
 * <p>Integer ranking 排名
 * <p>Boolean IsCal 是否计入学分绩点
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "score")
@Entity
public class Score implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer scoreId;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @NotBlank(message = "成绩不能为空")
    @DecimalMin(value = "0",message = "成绩必须大于0")
    @DecimalMax(value = "100",message = "成绩不能超过100")
    @Digits(integer = 20,fraction = 0,message = "成绩必须为整数")
    private Double mark;

    @NotBlank(message = "排名不能为空")
    @DecimalMin(value = "0",message = "排名必须大于0")
    @Digits(integer = 20,fraction = 0,message = "排名必须为整数")
    private Integer ranking;

    private Boolean IsCal;
}