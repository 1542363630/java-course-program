package com.example.courseprogram.model.DO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

/**
 * <p>SocietyMember 社会成员类
 * <p>Integer societyId 主键id
 * <p>Student student 对应的学生
 * <p>String gender  性别
 * <p>String relation 与学生的关系
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "society_member")
@Entity
public class SocietyMember implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer societyId;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    private String gender;
    @NotBlank(message = "关系不能为空")
    private String relation;
    @NotBlank(message = "姓名不能为空")
    private String name;

    private String phone;

}
