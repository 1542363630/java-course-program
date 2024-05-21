package com.example.courseprogram.repository;

import com.example.courseprogram.model.DO.SelectedCourseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface SelectedCourseInfoRepository extends JpaRepository<SelectedCourseInfo,Integer> {

    //根据课程查找
    List<SelectedCourseInfo> findSelectedCourseInfoByCourse_CourseId(Integer id);

    //根据学号查找
    @Query(value = "select s.selectedCourseInfo from SelectedCourse s where s.student.studentId=?1")
    List<SelectedCourseInfo> findByStudentId(Long id);

    //根据课程id删除
    void deleteByCourse_CourseId(Integer id);

    //根据课程编号或名称查询
    @Query(value = "select s from SelectedCourseInfo s where s.course.number like %?1% or s.course.name like %?1% or ?1='' ")
    List<SelectedCourseInfo> findByCourseNumberOrName(String numName);

    //根据教师名称查询
    @Query(value = "select s from SelectedCourseInfo s where s.course.teacherName like %?1%")
    List<SelectedCourseInfo> findByTeacherName(String name);
}
