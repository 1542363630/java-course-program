package com.example.courseprogram.repository;

import com.example.courseprogram.model.DO.DailyActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DailyActivityRepository extends JpaRepository<DailyActivity,Integer> {
    //根据学号查询
    List<DailyActivity> findDailyActivitiesByStudent_StudentId(Integer studentId);

    //根据学号和活动类型查询
    @Query(value = "select d from DailyActivity d where ?1 = d.student.studentId and ?2 = d.activityType")
    List<DailyActivity> findByStudentIdAndType(String number,String type);

    void deleteByStudent_StudentId(Integer studentId);
}
