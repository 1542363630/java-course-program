package com.example.courseprogram.repository;

import com.example.courseprogram.model.DO.LeaveInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveInfoRepository extends JpaRepository<LeaveInfo,Integer> {

    //根据学号查找
    List<LeaveInfo> findLeaveInfosByStudent_StudentId(Integer studentId);

    //根据是否回到学校查找
    List<LeaveInfo> findLeaveInfosByIsBackSchool(String isBackSchool);

    //根据学号删除
    void deleteByStudent_StudentId(Integer studentId);
}
