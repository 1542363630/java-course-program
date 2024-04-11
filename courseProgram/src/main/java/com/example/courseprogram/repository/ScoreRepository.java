package com.example.courseprogram.repository;

import com.example.courseprogram.model.DO.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Score,Integer> {

    List<Score> findScoresByStudent_StudentId(Integer studentId);

    List<Score> findScoresByCourse_CourseId(Integer courseId);

    void deleteScoresByStudent_StudentId(Integer studentId);

    void deleteScoresByCourse_CourseId(Integer courseId);


}
