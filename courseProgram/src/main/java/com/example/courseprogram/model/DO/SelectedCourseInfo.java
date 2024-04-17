package com.example.courseprogram.model.DO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
/**
 * <p>SelectedCourseInfo 课程的选课信息类
 * <p>Integer selectedCourseInfoId 主键id
 * <p>Course course 对应的课程
 * <p>Integer numberOfSelected 当前选课人数
 * <p>Integer MaxNumberOfSelected 最大选课人数
 * <p>
 */
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

    private Integer numberOfSelected;

    private Integer MaxNumberOfSelected;
}
