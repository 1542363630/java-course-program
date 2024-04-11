package com.example.courseprogram.repository;

import com.example.courseprogram.model.DO.DailyActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DailyActivityRepository extends JpaRepository<DailyActivity,Integer> {

    List<DailyActivity> findDailyActivitiesByStudent_StudentId(Integer studentId);

    void deleteByStudent_StudentId(Integer studentId);
}
