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
//@TableName("selected_course")
@Table(name = "selected_course")
@Entity
public class SelectedCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer selectedId;

    @ManyToOne
    @JoinColumn(name = "student_id")
//    @TableField("student_id")
    private Student student;

    @ManyToOne
//    @TableField("course_id")
    @JoinColumn(name = "course_id")
    private Course course;

}
