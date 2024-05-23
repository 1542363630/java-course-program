package com.example.courseprogram.model.DO;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.io.Serializable;
/**
 * <p>SelectedCourseInfo 课程的选课信息类
 * <p>Integer selectedCourseInfoId 主键id
 * <p>Course course 对应的课程
 * <p>Integer numberOfSelected 当前选课人数
 * <p>Integer MaxNumberOfSelected 最大选课人数
 * <p>
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "selected_course_info")
@Entity
public class SelectedCourseInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer selectedCourseInfoId;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    @NotNull(message = "当前选课人数不能为空")
    @DecimalMin(value = "0",message = "当前选课人数不能小于0")
    @Digits(integer = 10,fraction = 0,message = "当前选课人数必须为整数")
    private Integer numberOfSelected;
    @NotNull(message = "最大选课人数不能为空")
    @DecimalMin(value = "0",message = "最大选课人数不能小于0")
    @Digits(integer = 10,fraction = 0,message = "最大选课人数必须为整数")
    private Integer MaxNumberOfSelected;
}
