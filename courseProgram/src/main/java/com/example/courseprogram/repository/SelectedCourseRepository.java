package com.example.courseprogram.repository;

import com.example.courseprogram.model.DO.SelectedCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SelectedCourseRepository extends JpaRepository<SelectedCourse,Integer> {

    List<SelectedCourse> findSelectedCoursesByStudent_StudentId(Integer studentId);

    List<SelectedCourse> findSelectedCoursesByCourse_CourseId(Integer courseId);

    void deleteByStudent_StudentId(Integer studentId);

    void deleteByCourse_CourseId(Integer courseId);
}
