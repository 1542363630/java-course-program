package com.example.courseprogram.model.DO;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "innovative_practice_student")
@Entity
public class InnovativePracticeStudent implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer innovativePracticeStudentId;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name="innovative_practice_id")
    private InnovativePractice innovativePractice;
}
