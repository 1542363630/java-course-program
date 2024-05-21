package com.example.courseprogram.repository;

import com.example.courseprogram.model.DO.SelectedCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface SelectedCourseRepository extends JpaRepository<SelectedCourse,Integer> {

    //根据学号查找
    List<SelectedCourse> findSelectedCoursesByStudent_StudentId(Long studentId);

    //根据课程id查找
    List<SelectedCourse> findSelectedCoursesBySelectedCourseInfo_Course_CourseId(Integer courseId);

    //根据学号删除
    void deleteByStudent_StudentId(Long studentId);

    //根据课程id删除
    void deleteBySelectedCourseInfo_Course_CourseId(Integer courseId);

    //根据学号和选课信息id删除
    @Modifying
    @Query(value = "delete from SelectedCourse s where s.student.studentId=?1 and s.selectedCourseInfo.selectedCourseInfoId=?2")
    void deleteByStudent_StudentIdAndAndSelectedCourseInfo_SelectedCourseInfoId(Long studentId,Integer id);
}
