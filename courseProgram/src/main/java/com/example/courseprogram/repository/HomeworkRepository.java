package com.example.courseprogram.repository;

import com.example.courseprogram.model.DO.Homework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeworkRepository extends JpaRepository<Homework,Integer> {

    //根据学号找作业
    List<Homework> findHomeworkByStudent_StudentId(Integer studentId);

    //根据作业信息id找作业
    List<Homework> findHomeworkByHomeworkInfo_HomeworkInfoId(Integer homeworkInfoId);

    //根据学号删除
    void deleteByStudent_StudentId(Integer studentId);
}
