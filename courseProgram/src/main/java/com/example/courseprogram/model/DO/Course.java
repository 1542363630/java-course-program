package com.example.courseprogram.model.DO;

import com.example.courseprogram.Exception.AllowedValues;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.lang.NonNull;

import java.io.Serializable;

/**
 * <p>Course 课程表实体类  保存课程的的基本信息信息，
 * <p>Integer courseId 主键 course_id
 * <p>String number 课程编号
 * <p>String name 课程名称
 * <p>String credit 学分
 * <p>String courseTime 课程时间（一天中的时间）
 * <p>String teacherName 授课老师
 * <p>String courseBeginWeek 课程开始于第几周
 * <p>String courseEndWeek 课程结束于第几周
 * <p>String wayOfTest 考核方式
 * <p>String location 上课地点
 * <p>Course preCourse 前序课程 pre_course_id 关联前序课程的主键 course_id
 * <p>String type 课程类型
 * <p>String file 相关文件的路径
 */
@Data
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

    @NotBlank(message = "课程编号不能为空")
    @Size(max = 10,message = "课程编号不能超过10个字符")
    private String number;
    @NotBlank(message = "课程名称不能为空")
    private String name;
    @NotNull(message = "学分未填写")
    @DecimalMin(value = "0",message = "学分不能小于0")
    @DecimalMax(value = "10",message = "学分不能大于10")
    private Double credit;
    /**
     * 课程时间
     * 周一第一大节-周天第五大节
     */
    @NotBlank(message = "课程时间不能为空")
    private String courseTime;
    @NotBlank(message = "教师名称不能为空")
    private String teacherName;
    private String courseBeginWeek;
    private String courseEndWeek;
    /**
     * 考核方式
     * <p>考试、无、项目答辩、提交报告、其它</p>
     */
    @NotBlank(message = "考核方式不能为空")
    @AllowedValues(allowedValues = {"考试","无","项目答辩","提交报告","其它"},message = "考核方式必须为(考试、无、项目答辩、提交报告、其它)中的一个")
    private String wayOfTest;
    /**
     * 上课地点
     */
    @NotBlank(message = "上课地点不能为空")
    private String location;
    @ManyToOne
    @JoinColumn(name = "pre_course_id")
    private Course preCourse;
    /**
     * 课程类型 必修，选修，通选，限选，任选
     */
    @NotBlank(message = "课程类型不能为空")
    @AllowedValues(allowedValues = {"必修","选修","通选","限选","任选"},message = "课程类型必须为(必修，选修，通选，限选，任选)中的一个")
    private String type;

    private String file;
}
