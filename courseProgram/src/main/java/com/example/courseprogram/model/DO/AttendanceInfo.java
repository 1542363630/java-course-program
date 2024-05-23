package com.example.courseprogram.model.DO;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

/**
 * <p>Course 考勤信息实体类  保存课程或活动的考勤信息
 * <p>Integer attendanceId 主键
 * <p>Student student 对应学生 student_id
 * <p>String activityName 活动名称
 * <p>String type 活动类型
 * <p>String attendanceTime 考勤时间
 * <p>String isAttended 是否考勤
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "attendance_info")
@Entity
public class AttendanceInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer attendanceId;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @NotBlank(message = "活动名称不能为空")
    @Size(max = 100 , message = "活动名称长度不能超过50个字")
    private String activityName;
    /**
     * 上课考勤、会议考勤、活动考勤
     */
    @NotBlank(message = "考勤类型不能为空")
    private String type;

    @NotBlank(message = "考勤时间不能为空")
    private String attendanceTime;

    /**
     * 已考勤、未考勤
     */
    @NotBlank(message = "考勤状态不能为空")
    private String isAttended;
}
