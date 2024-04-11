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
//@TableName("honor_info")
@Table(name = "honor_info")
@Entity
public class HonorInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer honorId;

    @ManyToOne
    @JoinColumn(name = "student_id")
//    @TableField("student_id")
    private Student student;
    private String proveInfo;

    private String level;

    private String honorTime;

    private String honorReason;

    private String honorFrom;

    private String honorName;
}
