package com.example.courseprogram.repository;

import com.example.courseprogram.model.DO.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person,Integer> {
    //根据 number 查找
    Person findByNumber(Long number);

    //根据政治面貌查找
    List<Person> findPersonByPoliticalStatus(String politicalStatus);

    //根据用户名查找
    @Query(value = "select u.person from Person p,User u where u.userName  like %?1%")
    Person findPersonByUserName(String userName);


}
