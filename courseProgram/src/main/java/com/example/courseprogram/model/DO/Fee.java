package com.example.courseprogram.model.DO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * Fee 消费流水实体类  保存学生消费流水的基本信息信息，
 * Integer feeId 消费表 fee 主键 fee_id
 * Integer studentId  student_id 对应student 表里面的 student_id
 * String day 日期
 * Double money 金额
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@TableName("fee")
@Table(name = "fee")
@Entity
public class Fee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer feeId;
    @ManyToOne
    @JoinColumn(name = "student_id")
//    @TableField("student_id")
    private Student student;
    private String day;
    private Double money;
}
