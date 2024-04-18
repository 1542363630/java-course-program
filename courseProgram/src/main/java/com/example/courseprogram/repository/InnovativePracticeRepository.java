package com.example.courseprogram.repository;

import com.example.courseprogram.model.DO.InnovativePractice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InnovativePracticeRepository extends JpaRepository<InnovativePractice,Integer> {

    //根据学号查找
    List<InnovativePractice> findInnovativePracticesByStudent_StudentId(Integer studentId);

    //根据类型查找
    List<InnovativePractice> findInnovativePracticesByType(String type);

    //根据学号删除
    void deleteByStudent_StudentId(Integer studentId);
}
