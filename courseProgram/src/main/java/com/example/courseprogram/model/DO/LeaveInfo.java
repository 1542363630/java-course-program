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
//@TableName("leave_info")
@Table(name = "leave_info")
@Entity
public class LeaveInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer leaveId;

    @ManyToOne
    @JoinColumn(name = "student_id")
//    @TableField("student_id")
    private Student student;

    private String leaveBeginTime;

    private String leaveEndTime;

    private String isParentKnow;

    private String approver;

    private String leaveReason;

}
