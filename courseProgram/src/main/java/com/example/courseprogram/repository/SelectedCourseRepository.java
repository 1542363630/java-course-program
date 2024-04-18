package com.example.courseprogram.repository;

import com.example.courseprogram.model.DO.SelectedCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SelectedCourseRepository extends JpaRepository<SelectedCourse,Integer> {

    //根据学号查找
    List<SelectedCourse> findSelectedCoursesByStudent_StudentId(Integer studentId);

    //根据课程id查找
    List<SelectedCourse> findSelectedCoursesByCourse_CourseId(Integer courseId);

    //根据学号删除
    void deleteByStudent_StudentId(Integer studentId);

    //根据课程id删除
    void deleteByCourse_CourseId(Integer courseId);
}
