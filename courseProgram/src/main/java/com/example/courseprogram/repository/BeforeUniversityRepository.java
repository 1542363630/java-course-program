package com.example.courseprogram.repository;

import com.example.courseprogram.model.DO.BeforeUniversity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface BeforeUniversityRepository extends JpaRepository<BeforeUniversity,Integer> {

    //根据学号删除
    @Modifying
    @Query(value = "delete from BeforeUniversity b where b.student.studentId = ?1")
    void deleteByStudentId(@NonNull Long studentId);

    void deleteBeforeUniversityByStudent_StudentId(Long studentId);

    //根据学号查找
    BeforeUniversity findBeforeUniversityByStudent_StudentId(Long studentId);

    @Query(value = "select b from BeforeUniversity b where b.student.studentId = ?1")
    BeforeUniversity selectByStudentId(Long studentId);

    @Modifying
    @Query(value = "update BeforeUniversity b set b.graduatedSchool=?1 where b.student.studentId=?2")
    void updateGraduatedSchoolByStudentId(String newGraduatedSchool,Long studentId);

}
