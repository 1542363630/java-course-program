package com.example.courseprogram.repository;

import com.example.courseprogram.model.DO.Student;
import com.example.courseprogram.model.DO.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long> {
    @Modifying
    @Query(value = "update Teacher t set t = ?1 where t.teacherId = ?2")
    Teacher updateTeacherByTeacherId(Teacher teacher,Long teacherId);

    boolean existsTeacherByPerson_Number(String number);

    @Query(value = "select t from Teacher t,User u where u.userId=?1 and t.person.personId=u.person.personId")
    Teacher findByUserId(Integer userId);

    //根据工号或姓名查询教师
    @Query(value = "select t from Teacher t where t.person.number like %?1% or t.person.name like %?1%")
    List<Teacher> findByTeacherIdOrName(String numName);
}
