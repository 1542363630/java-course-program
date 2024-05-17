package com.example.courseprogram.repository;

import com.example.courseprogram.model.DO.DailyActivity;
import com.example.courseprogram.model.DO.DailyActivityStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface DailyActivityStudentRepository extends JpaRepository<DailyActivityStudent,Integer> {

//    //根据学号和活动类型查询
//    @Query(value = "select d from DailyActivityStudent d where ?1 = d.student.studentId and ?2 = d.dailyActivity.activityType")
//    List<DailyActivityStudent> findByStudentIdAndType(Long id,String type);

    //根据学号查询
    List<DailyActivityStudent> findDailyActivityStudentsByStudent_StudentId(Long studentId);

    //根据学号删除
    void deleteByStudent_StudentId(Long studentId);

    //根据活动编号查询
    List<DailyActivityStudent> findDailyActivityStudentsByDailyActivity_ActivityId(Integer activityId);

    //根据活动编号删除
    void deleteByDailyActivity_ActivityId(Integer activityId);



}
