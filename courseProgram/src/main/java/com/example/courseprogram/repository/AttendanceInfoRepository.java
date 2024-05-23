package com.example.courseprogram.repository;

import com.example.courseprogram.model.DO.AttendanceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface AttendanceInfoRepository extends JpaRepository<AttendanceInfo,Integer> {

    List<AttendanceInfo> findAttendanceInfosByStudent_StudentId(Long studentId);

    List<AttendanceInfo> findAttendanceInfosByIsAttended(String isAttended);

    //根据学号和是否考勤查找
    @Query(value = "select ai from AttendanceInfo ai where ai.student.studentId = ?1 and (ai.isAttended like %?2% or ?2 = '')")
    List<AttendanceInfo> findByStudentIdAndIsAttended(Long id,String isAttended);

    void deleteByStudent_StudentId(Long studentId);

    //根据类型查询
    List<AttendanceInfo> findAttendanceInfoByType(String type);

}
