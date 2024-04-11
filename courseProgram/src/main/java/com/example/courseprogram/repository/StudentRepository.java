package com.example.courseprogram.repository;

import com.example.courseprogram.model.DO.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

    @Modifying
    @Query(value = "update Student s set s = ?1 where s.studentId = ?2")
    Student updateStudentByStudentId(Student student,Integer studentId);

    boolean existsStudentByPerson_Number(String number);

    @Query(value = "select s from Student s,User u where u.userId=?1 and s.person.personId=u.person.personId")
    Student findByUserId(Integer userId);

    Student findStudentByPersonNumber(String number);
}
