package com.example.courseprogram.repository;

import com.example.courseprogram.model.DO.Homework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeworkRepository extends JpaRepository<Homework,Integer> {

    List<Homework> findHomeworkByStudent_StudentId(Integer studentId);

    void deleteByStudent_StudentId(Integer studentId);
}
