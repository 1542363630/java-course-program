package com.example.courseprogram.repository;

import com.example.courseprogram.model.DO.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Integer> {
    @Query(value = "select c from Course c where c.num like %?1%")
    List<Course> findCourseListByNum(String number);

    @Query(value = "select c from Course c where c.name like %?1%")
    List<Course> findCourseListByName(String CourseName);

    void deleteCourseByCourseId(Integer courseId);

}
