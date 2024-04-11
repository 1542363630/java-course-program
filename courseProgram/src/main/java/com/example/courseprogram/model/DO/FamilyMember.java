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
//@TableName("family_member")
@Table(name = "family_member")
@Entity
public class FamilyMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer memberId;

    @ManyToOne
    @JoinColumn(name = "student_id")
//    @TableField("student_id")
    private Student student;
    private String relation;
    private String name;
    private String gender;
    private Integer age;
    private String unit;
}
