package com.example.courseprogram.repository;

import com.example.courseprogram.model.DO.DailyActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface DailyActivityRepository extends JpaRepository<DailyActivity,Integer> {
    //根据学号查询
    @Query(value = "select d.dailyActivity from DailyActivityStudent d where d.student.studentId = ?1")
    List<DailyActivity> findDailyActivitiesByStudent_StudentId(Long studentId);

    //根据学号和活动类型查询
    @Query(value = "select d.dailyActivity from DailyActivityStudent d where d.student.studentId = ?1 and d.dailyActivity.activityType = ?2")
    List<DailyActivity> findByStudentIdAndType(Long studentId,String type);

    //根据活动类型查询
    @Query(value = "select d from DailyActivity d where ?1 = d.activityType")
    List<DailyActivity> findByType(String type);

}
