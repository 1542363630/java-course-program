package com.example.courseprogram.repository;

import com.example.courseprogram.model.DO.HonorInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface HonorInfoRepository extends JpaRepository<HonorInfo,Integer> {

    //根据学号查找
    List<HonorInfo> findHonorInfosByStudent_StudentId(Long studentId);

    //根据荣誉类别查找
    List<HonorInfo> findHonorInfosByType(String type);

    //根据荣誉名称查找
    @Query(value = "select hi from HonorInfo hi where hi.honorName like %?1% or ?1='' ")
    List<HonorInfo> findByName(String name);

    //根据荣誉名根据学号和荣誉名称查询称查找
    @Query(value = "select hi from HonorInfo hi where hi.student.studentId = ?1 and (hi.honorName like %?2% or ?2='') ")
    List<HonorInfo> findByStudentIdAndHonorName(Long id,String name);

    //根据学号删除
    void deleteByStudent_StudentId(Long studentId);
}
