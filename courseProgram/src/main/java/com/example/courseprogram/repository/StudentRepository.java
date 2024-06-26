package com.example.courseprogram.repository;

import com.example.courseprogram.model.DO.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    //根据学号更新
    @Modifying
    @Query(value = "update Student s set s = ?1 where s.studentId = ?2")
    Student updateStudentByStudentId(Student student,Long studentId);

    //根据学号查找是否有对应学生已存在
    boolean existsStudentByPerson_Number(String number);

    //根据用户id查找
    @Query(value = "select s from Student s,User u where u.userId=?1 and s.person.personId=u.person.personId")
    Student findByUserId(Integer userId);

    //根据学号或姓名查询学生
    @Query(value = "select s from Student s where s.person.number like %?1% or s.person.name like %?1%")
    List<Student> findByStudentIdOrName(String numName);

}
