package com.example.courseprogram.repository;

import com.example.courseprogram.model.DO.Homework;
import com.example.courseprogram.model.DO.Score;
import com.example.courseprogram.model.DO.SelectedCourseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface ScoreRepository extends JpaRepository<Score,Integer> {

    //根据学号查找
    List<Score> findScoresByStudent_StudentId(Long studentId);

    //根据课程查找
    List<Score> findScoresByCourse_CourseId(Integer courseId);

    //根据学号删除
    void deleteScoresByStudent_StudentId(Long studentId);

    //根据课程删除
    void deleteScoresByCourse_CourseId(Integer courseId);

    //根据课程编号或名称查询
    @Query(value = "select s from Score s where s.course.number like %?1% or s.course.name like %?1% or ?1='' ")
    List<Score> findByCourseNumberOrName(String numName);

    //根据学号和(课程编号或名称)查询
    @Query(value = "select s from Score s where s.student.studentId = ?1 and (s.course.number like %?2% or s.course.name like %?2%)")
    List<Score> findByStudentIdAndNumName(Long id, String numName);

}
