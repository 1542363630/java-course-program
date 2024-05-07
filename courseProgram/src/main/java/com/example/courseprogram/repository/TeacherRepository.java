package com.example.courseprogram.repository;

import com.example.courseprogram.model.DO.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long> {
    @Modifying
    @Query(value = "update Teacher t set t = ?1")
    Teacher updateTeacherByTeacherId(Teacher teacher);

    boolean existsTeacherByPerson_Number(Long number);

    @Query(value = "select t from Teacher t,User u where u.userId=?1 and t.person.personId=u.person.personId")
    Teacher findByUserId(Integer userId);
}
