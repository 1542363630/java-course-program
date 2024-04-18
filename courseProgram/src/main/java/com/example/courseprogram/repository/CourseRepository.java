package com.example.courseprogram.repository;

import com.example.courseprogram.model.DO.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Integer> {
    //根据课程编号查找
    @Query(value = "select c from Course c where c.number like %?1%")
    List<Course> findCourseListByNumber(String number);

    //根据课程名称查找
    @Query(value = "select c from Course c where c.name like %?1%")
    List<Course> findCourseListByName(String CourseName);

    //根据课程类型查找
    @Query(value = "select c from Course c where c.type like %?1%")
    List<Course> findCourseListByType(String CourseType);

    //根据课程id删除
    void deleteCourseByCourseId(Integer courseId);

}
