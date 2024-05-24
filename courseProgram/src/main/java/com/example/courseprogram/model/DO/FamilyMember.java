package com.example.courseprogram.model.DO;

import com.example.courseprogram.Exception.AllowedValues;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

/**
 * <p>FamilyMember 家庭成员实体类
 * <p>Integer memberId 主键id
 * <p>Student student 对应学生
 * <p>String relation 与学生的关系
 * <p>String name 姓名
 * <p>String gender 性别
 * <p>String unit 工作单位
 * <p>String birthday 出生日期
 * <p>String phone 联系电话
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "family_member")
@Entity
public class FamilyMember implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer memberId;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @NotBlank(message = "关系不能为空")
    private String relation;
    @NotBlank(message = "姓名不能为空")
    private String name;

    private String phone;

    /**
     * 性别：
     * <p>男、女</p>
     */
    @NotBlank(message = "性别不能为空")
    @AllowedValues(allowedValues = {"男","女"},message = "性别必须为(男、女)中的一个")
    private String gender;

    private String birthday;

    private String unit;
}
