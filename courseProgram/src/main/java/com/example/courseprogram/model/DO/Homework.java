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
//@TableName("homework")
@Table(name = "homework")
@Entity
public class Homework {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer homeworkId;

    private String homeworkScore;

    private String isSubmit;
    @ManyToOne
//    @TableField("course_id")
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "student_id")
//    @TableField("student_id")
    private Student student;
}
