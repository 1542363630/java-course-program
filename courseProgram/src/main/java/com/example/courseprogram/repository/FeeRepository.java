package com.example.courseprogram.repository;

import com.example.courseprogram.model.DO.Fee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeeRepository extends JpaRepository<Fee,Integer> {
    List<Fee> findFeesByStudent_StudentId(Integer studentId);

    void deleteByStudent_StudentId(Integer studentId);
}
