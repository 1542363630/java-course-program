package com.example.courseprogram.repository;

import com.example.courseprogram.model.DO.AttendanceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface AttendanceInfoRepository extends JpaRepository<AttendanceInfo,Integer> {

    List<AttendanceInfo> findAttendanceInfosByStudent_StudentId(Long studentId);

    List<AttendanceInfo> findAttendanceInfosByIsAttended(String isAttended);

    void deleteByStudent_StudentId(Long studentId);

}
