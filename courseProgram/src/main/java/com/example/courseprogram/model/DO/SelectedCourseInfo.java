package com.example.courseprogram.model.DO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "selected_course_info")
@Entity
public class SelectedCourseInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer selectedCourseInfoId;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;


    private Integer numberOfSelected;
    private Integer MaxNumberOfSelected;
}
