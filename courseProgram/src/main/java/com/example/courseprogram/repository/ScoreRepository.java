package com.example.courseprogram.repository;

import com.example.courseprogram.model.DO.Score;
import org.springframework.data.jpa.repository.JpaRepository;
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


}
