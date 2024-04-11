package com.example.courseprogram.repository;

import com.example.courseprogram.model.DO.FamilyMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FamilyMemberRepository extends JpaRepository<FamilyMember,Integer> {
    List<FamilyMember> findFamilyMembersByStudent_StudentId(Integer studentId);

    void deleteByStudent_StudentId(Integer studentId);
}
