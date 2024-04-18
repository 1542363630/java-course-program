package com.example.courseprogram.repository;

import com.example.courseprogram.model.DO.SocietyMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SocietyMemberRepository extends JpaRepository<SocietyMember,Integer> {

    //根据学号查找
    List<SocietyMember> findSocietyMembersByStudent_StudentId(Integer studentId);

    //根据学号删除
    void deleteByStudent_StudentId(Integer studentId);
}
