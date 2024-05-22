package com.example.courseprogram.repository;

import com.example.courseprogram.model.DO.LeaveInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface LeaveInfoRepository extends JpaRepository<LeaveInfo,Integer> {

    //根据学号查找
    List<LeaveInfo> findLeaveInfosByStudent_StudentId(Long studentId);

    //根据是否回到学校查找
    List<LeaveInfo> findLeaveInfosByLeaveStatus(String LeaveStatus);

    //根据学号删除
    void deleteByStudent_StudentId(Long studentId);
}
