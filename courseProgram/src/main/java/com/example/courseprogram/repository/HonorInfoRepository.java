package com.example.courseprogram.repository;

import com.example.courseprogram.model.DO.HonorInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HonorInfoRepository extends JpaRepository<HonorInfo,Integer> {

    //根据学号查找
    List<HonorInfo> findHonorInfosByStudent_StudentId(Integer studentId);

    //根据荣誉类别查找
    List<HonorInfo> findHonorInfosByType(String type);

    //根据学号删除
    void deleteByStudent_StudentId(Integer studentId);
}
