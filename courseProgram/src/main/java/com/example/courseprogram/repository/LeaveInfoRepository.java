package com.example.courseprogram.repository;

import com.example.courseprogram.model.DO.LeaveInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveInfoRepository extends JpaRepository<LeaveInfo,Integer> {

    List<LeaveInfo> findLeaveInfosByStudent_StudentId(Integer studentId);

    void deleteByStudent_StudentId(Integer studentId);
}
