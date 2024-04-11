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
//@TableName("society_member")
@Table(name = "society_member")
@Entity
public class SocietyMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer societyId;

    @ManyToOne
    @JoinColumn(name = "student_id")
//    @TableField("student_id")
    private Student student;

    private String age;

    private String gender;

    private String relation;

    private String unit;

}
