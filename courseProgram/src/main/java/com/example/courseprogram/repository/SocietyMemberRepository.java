package com.example.courseprogram.repository;

import com.example.courseprogram.model.DO.SocietyMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SocietyMemberRepository extends JpaRepository<SocietyMember,Integer> {

    List<SocietyMember> findSocietyMembersByStudent_StudentId(Integer studentId);

    void deleteByStudent_StudentId(Integer studentId);
}
