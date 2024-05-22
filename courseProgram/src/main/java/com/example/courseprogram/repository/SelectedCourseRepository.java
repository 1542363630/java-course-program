package com.example.courseprogram.repository;

import com.example.courseprogram.model.DO.SelectedCourse;
import com.example.courseprogram.model.DO.SelectedCourseInfo;
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

    @Query(value = "select s.selectedCourseInfo from SelectedCourse s where s.student.studentId = ?1 and (s.selectedCourseInfo.course.type like %?2%)")
    List<SelectedCourseInfo> findByStudentIdAndType(Long id,String type);

    @Query(value = "select s.selectedCourseInfo from SelectedCourse s where s.student.studentId = ?1 and (s.selectedCourseInfo.course.number like %?2% or s.selectedCourseInfo.course.name like %?2%)")
    List<SelectedCourseInfo> findByStudentIdAndNumName(Long id,String numName);

    @Query(value = "select s.selectedCourseInfo from SelectedCourse s where s.student.studentId = ?1 and (s.selectedCourseInfo.course.teacherName like %?2%)")
    List<SelectedCourseInfo> findByStudentIdAndTeacherName(Long id,String teacherName);
}
