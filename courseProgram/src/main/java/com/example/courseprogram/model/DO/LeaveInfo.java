package com.example.courseprogram.model.DO;
/**
 * <p>LeaveInfo 请假信息类
 * <p>Integer leaveId 主键id
 * <p>Student student 对应学生
 * <p>String leaveBeginTime 请假开始时间
 * <p>String leaveEndTime 请假结束时间
 * <p>String leaveTime 请假时间
 * <p>String approver 批准人
 * <p>String leaveReason 请假原因
 * <p>String leaveStatus 请假状态
 */
import com.example.courseprogram.Exception.AllowedValues;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "leave_info")
@Entity
public class LeaveInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer leaveId;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @NotBlank(message = "请假开始不能为空")
    private String leaveBeginTime;
    @NotBlank(message = "请假结束不能为空")
    private String leaveEndTime;
    @NotBlank(message = "请假时间不能为空")
    private String leaveTime;
    @NotBlank(message = "批准人不能为空")
    private String approver;
    @NotBlank(message = "请假原因不能为空")
    private String leaveReason;
    @NotBlank(message = "请假状态不能为空")
    @AllowedValues(allowedValues = {"待审核","已批准","已拒绝"},message = "请假状态必须为(待审核,已批准,已拒绝)中的一个")
    private String leaveStatus;
}
