package com.example.courseprogram.repository;

import com.example.courseprogram.model.DO.Fee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface FeeRepository extends JpaRepository<Fee,Integer> {

    //根据学号查找 按时间从晚到早
    @Query(value = "select f from Fee f where f.student.studentId=?1 order by f.day desc")
    List<Fee> findFeesByStudent_StudentId(Long studentId);


    //根据学生id和日期查询
    @Query(value = "select f from Fee f where f.student.studentId=?1 and f.day = ?2")
    List<Fee> findByStudentIdAndDay(Long studentId, String day);

    //根据学号删除
    void deleteByStudent_StudentId(Long studentId);
}
