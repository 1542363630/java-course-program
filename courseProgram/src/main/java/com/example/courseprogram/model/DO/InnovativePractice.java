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
//@TableName("innovative_practice")
@Table(name = "innovative_practice")
@Entity
public class InnovativePractice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer innovativeId;

    @ManyToOne
    @JoinColumn(name = "student_id")
//    @TableField("student_id")
    private Student student;

    private String teacherName;

    private String location;

    private String beginTime;

    private String endTime;

    private String type;
}
