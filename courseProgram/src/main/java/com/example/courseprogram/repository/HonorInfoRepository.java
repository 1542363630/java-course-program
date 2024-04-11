package com.example.courseprogram.repository;

import com.example.courseprogram.model.DO.HonorInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HonorInfoRepository extends JpaRepository<HonorInfo,Integer> {
    List<HonorInfo> findHonorInfosByStudent_StudentId(Integer studentId);

    void deleteByStudent_StudentId(Integer studentId);
}
