package com.example.courseprogram.repository;

import com.example.courseprogram.model.DO.FamilyMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface FamilyMemberRepository extends JpaRepository<FamilyMember,Integer> {

    //根据学号查找
    List<FamilyMember> findFamilyMembersByStudent_StudentId(Long studentId);

    //根据学号删除
    void deleteByStudent_StudentId(Long studentId);
}
