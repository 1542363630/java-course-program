package com.example.courseprogram.model.DO;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.io.Serializable;

/**
 * <p>Fee 消费流水实体类  保存学生消费流水的基本信息信息，
 * <p>Integer feeId 消费表 fee 主键 fee_id
 * <p>Integer studentId  student_id 对应student 表里面的 student_id
 * <p>String day 消费日期
 * <p>String feeType 消费项目，又叫消费类别 比如饮食、购物啥的
 * <p>Double money 消费金额
 * <p>String description 消费描述，描述具体买了啥
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "fee")
@Entity
public class Fee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer feeId;

    @NotBlank(message = "消费类型不能为空")
    private String feeType;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @NotBlank(message = "消费日期不能为空")
    private String day;
    @NotNull(message = "消费金额未填写")
    @DecimalMin(value = "0",message = "消费金额不能小于0")
    @DecimalMax(value = "100000000",message = "消费金额不能大于100000000")
    private Double money;
    @NotBlank(message = "消费描述不能为空")
    @Size(max=200,message = "消费描述不能超过100个字")
    private String description;
}
