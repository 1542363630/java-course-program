package com.example.courseprogram.repository;

import com.example.courseprogram.model.DO.FamilyMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FamilyMemberRepository extends JpaRepository<FamilyMember,Integer> {

    //根据学号查找
    List<FamilyMember> findFamilyMembersByStudent_StudentId(Integer studentId);

    //根据学号删除
    void deleteByStudent_StudentId(Integer studentId);
}
