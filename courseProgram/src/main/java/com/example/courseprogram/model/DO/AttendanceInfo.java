package com.example.courseprogram.model.DO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@TableName("attendance_info")
@Table(name = "attendance_info")
@Entity
public class AttendanceInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer attendanceId;

    @ManyToOne
//    @TableField("student_id")
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
//    @TableField("course_id")
    @JoinColumn(name = "course_id")
    private Course course;

    private String attendanceTime;

    private String isAttended;
}
