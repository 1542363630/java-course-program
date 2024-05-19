package com.example.courseprogram.repository;

import com.example.courseprogram.model.DO.InnovativePracticeStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface InnovativePracticeStudentRepository extends JpaRepository<InnovativePracticeStudent,Integer> {

    //根据活动id和学号删除
    @Modifying
    @Query(value = "delete from InnovativePracticeStudent i where ?1 = i.innovativePractice.innovativeId and ?2 = i.student.studentId")
    void deleteByIdAndStudents(Integer id,Long studentId);

    //根据活动id和学号判断存在
    @Query(value = "select count(i) from InnovativePracticeStudent i where ?1 = i.innovativePractice.innovativeId and ?2 = i.student.studentId")
    Integer selectByIdAndStudentId(Integer id, Long studentId);
    
    //根据学号查询
    List<InnovativePracticeStudent> findInnovativePracticeStudentsByStudent_StudentId(Long studentId);

    //根据学号删除
    void deleteByStudent_StudentId(Long studentId);

    //根据活动编号查询
    List<InnovativePracticeStudent> findInnovativePracticeStudentsByInnovativePractice_InnovativeId(Integer innovativeId);

    //根据活动编号删除
    void deleteByInnovativePractice_InnovativeId(Integer innovativeId);
}
