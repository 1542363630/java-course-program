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
//@TableName("before_university")
@Table(name = "before_university")
@Entity
public class BeforeUniversity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer BeforeUniversityId;

    @OneToOne
//    @TableField("student_id")
    @JoinColumn(name = "student_id")
    private Student student;

    private String graduatedProvince;

    private String graduatedSchool;
}
