package com.example.courseprogram.repository;

import com.example.courseprogram.model.DO.InnovativePractice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InnovativePracticeRepository extends JpaRepository<InnovativePractice,Integer> {

    List<InnovativePractice> findInnovativePracticesByStudent_StudentId(Integer studentId);

    void deleteByStudent_StudentId(Integer studentId);
}
