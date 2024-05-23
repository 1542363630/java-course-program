package com.example.courseprogram.repository;

import com.example.courseprogram.model.DO.DailyActivity;
import com.example.courseprogram.model.DO.InnovativePractice;
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

    //根据学号和活动名称查询
    @Query(value = "select d.dailyActivity from DailyActivityStudent d where d.student.studentId = ?1 and (d.dailyActivity.activityName like %?2% or ?2 = '')")
    List<DailyActivity> findByStudentIdAndName(Long studentId,String name);

    //根据活动类型查询
    @Query(value = "select d from DailyActivity d where ?1 = d.activityType")
    List<DailyActivity> findByType(String type);

    //根据活动名称
    @Query(value = "select da from DailyActivity da where da.activityName like %?1% or ?1='' ")
    List<DailyActivity> findByName(String name);

}
