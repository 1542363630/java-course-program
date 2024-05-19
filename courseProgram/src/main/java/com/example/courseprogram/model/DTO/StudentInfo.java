package com.example.courseprogram.model.DTO;

import com.example.courseprogram.model.DO.Person;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentInfo extends Person {

    private String major;

    private String className;

    private String GPA;
}
