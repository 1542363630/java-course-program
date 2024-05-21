package com.example.courseprogram.repository;

import com.example.courseprogram.model.DO.DailyActivity;
import com.example.courseprogram.model.DO.InnovativePractice;
import com.example.courseprogram.model.DO.SelectedCourseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface InnovativePracticeRepository extends JpaRepository<InnovativePractice,Integer> {

    //根据学号查找
    @Query(value = "select i.innovativePractice from InnovativePracticeStudent i where i.student.studentId = ?1")
    List<InnovativePractice> findInnovativePracticesByStudent_StudentId(Long studentId);

    //根据学号和活动类型查询
    @Query(value = "select i.innovativePractice from InnovativePracticeStudent i where i.student.studentId = ?1 and i.innovativePractice.type = ?2")
    List<InnovativePractice> findByStudentIdAndType(Long studentId, String type);

    //根据类型查找
    List<InnovativePractice> findInnovativePracticesByType(String type);

    //根据活动名称
    @Query(value = "select ip from InnovativePractice ip where ip.activityName like %?1% or ?1='' ")
    List<InnovativePractice> findByName(String name);

}
